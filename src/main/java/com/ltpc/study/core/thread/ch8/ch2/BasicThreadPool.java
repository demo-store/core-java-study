package com.ltpc.study.core.thread.ch8.ch2;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created with IntelliJ IDEA.
 *
 * @author liutong
 * @date 2019-06-14 16:28
 * Description:
 **/
public class BasicThreadPool extends Thread implements ThreadPool {

    /**
     * 初始化线程数量
     */
    private final int initSize;
    /**
     * 线程池最大线程数量
     */
    private final int maxSize;
    /**
     * 线程池核心线程数量
     */
    private final int coreSize;
    /**
     * 当前活跃线程数
     */
    private int activeCount;
    /**
     * 创建线程所需工厂
     */
    private final ThreadFactory threadFactory;
    /**
     * 任务队列
     */
    private final RunnableQueue runnableQueue;
    /**
     * 线程是否已经被shutdown
     */
    private volatile boolean isShutdown = false;
    /**
     * 工作线程队列
     */
    private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();

    private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();

    private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();

    private final long keepAliveTime;

    private final TimeUnit timeUnit;

    public BasicThreadPool(int initSize,int maxSize, int coreSize,int queueSize){
        this(initSize,maxSize,coreSize,DEFAULT_THREAD_FACTORY,
                queueSize,DEFAULT_DENY_POLICY,10,TimeUnit.SECONDS);
    }

    public BasicThreadPool(int initSize,int maxSize, int coreSize,ThreadFactory threadFactory,
                           int queueSize,DenyPolicy denyPolicy,long keepAliveTime,TimeUnit timeUnit){
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.runnableQueue = new LinkedRunnableQueue(queueSize,denyPolicy,this);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit =timeUnit;
        this.init();
    }

    private void init(){
        start();
        for (int i = 0; i < initSize; i++) {
            newThread();
        }
    }
    /**
     * 提交任务到线程池
     *
     * @param runnable
     */
    @Override
    public void execute(Runnable runnable) {
        if (this.isShutdown){
            throw new IllegalStateException("The thread pool is destroy");
        }
        this.runnableQueue.offer(runnable);
    }

    /**
     * 关闭线程池
     */
    @Override
    public void shutdown() {
        synchronized (this){
            if (isShutdown){
                return;
            }
            isShutdown = true;
            threadQueue.forEach(threadTask -> {
                threadTask.internalTask.stop();
                threadTask.thread.interrupt();
            });
            this.interrupt();
        }
    }

    /**
     * 获取线程池的初始化大小
     *
     * @return
     */
    @Override
    public int getInitSize() {
        if (isShutdown){
            throw new IllegalStateException("The thread pool is destory");
        }
        return this.initSize;
    }

    /**
     * 获取线程池最大的线程数
     *
     * @return
     */
    @Override
    public int getMaxSize() {
        if (isShutdown){
            throw new IllegalStateException("The thread pool is destory");
        }
        return this.maxSize;
    }

    /**
     * 获取线程池的核心线程数量
     *
     * @return
     */
    @Override
    public int getCoreSize() {
        if (isShutdown){
            throw new IllegalStateException("The thread pool is destory");
        }
        return this.coreSize;
    }

    /**
     * 获取线程池中用于缓存任务队列的大小
     *
     * @return
     */
    @Override
    public int getQueueSize() {
        if (isShutdown){
            throw new IllegalStateException("The thread pool is destory");
        }
        return runnableQueue.size();
    }

    /**
     * 获取线程池中活跃线程的数量
     *
     * @return
     */
    @Override
    public int getActiveCount() {
        synchronized (this){
            return this.activeCount;
        }
    }

    /**
     * 查看线程池是否已经被shutdown
     *
     * @return
     */
    @Override
    public boolean isShutdown() {
        return this.isShutdown;
    }

    @Override
    public void run(){
        // run方法集成Thread，主要用于维护线程数量，比如扩容、回收等工作。
        while (!isShutdown && !isInterrupted()){
            try {
                timeUnit.sleep(keepAliveTime);
            } catch (InterruptedException e) {
                isShutdown = true;
                break;
            }
            synchronized (this){
                if (isShutdown){
                    break;
                }
                // 若任务队列中存在任务未处理，且活跃线程数小于核心线程数则继续扩容
                if (runnableQueue.size() > 0 && activeCount < coreSize){
                    for (int i = initSize; i < coreSize; i++) {
                        newThread();
                    }
                    // 不要让线程的扩容直接到maxsize
                    continue;
                }
                // 若任务队列中存在任务未处理，且活跃线程数小于最大线程数则继续扩容
                if (runnableQueue.size() > 0 && activeCount < maxSize){
                    for (int i = coreSize; i < maxSize; i++) {
                        newThread();
                    }
                }
                // 若任务队列中不存在未处理任务，则回收线程指核心线程数量
                if (runnableQueue.size() == 0 && activeCount > coreSize){
                    for (int i = coreSize; i < activeCount; i++) {
                        removeThread();
                    }
                }
            }
        }
    }

    private void newThread(){
        //创建任务线程，并且启动
        InternalTask internalTask = new InternalTask(runnableQueue);
        Thread thread=this.threadFactory.createThread(internalTask);
        ThreadTask threadTask=new ThreadTask(thread,internalTask);
        threadQueue.offer(threadTask);
        this.activeCount++;
        thread.start();
    }

    private void removeThread(){
        ThreadTask threadTask = threadQueue.remove();
        threadTask.internalTask.stop();
        this.activeCount --;
    }

    /**
     *
     */
    private static class ThreadTask{
        Thread thread;
        InternalTask internalTask;

        public ThreadTask(Thread thread, InternalTask internalTask) {
            this.thread = thread;
            this.internalTask = internalTask;
        }
    }

    private static class DefaultThreadFactory implements ThreadFactory{

        public static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);

        public static final AtomicInteger COUNTER = new AtomicInteger(0);

        public static final ThreadGroup group = new ThreadGroup("ltThreadGroup-"+GROUP_COUNTER.getAndDecrement());

        @Override
        public Thread createThread(Runnable runnable) {
            return new Thread(group, runnable, "thread-pool-"+COUNTER.getAndDecrement());
        }
    }
}
