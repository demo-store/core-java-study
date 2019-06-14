package com.ltpc.study.core.thread.ch8.ch2;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liutong
 * @date 2019-06-14 15:14
 * Description:
 **/
public class LinkedRunnableQueue implements RunnableQueue {
    /**
     * 任务队列的最大容量，在构造时进行初始化
     */
    private final int limit;

    /**
     * 若任务队列已满，则需要执行的拒绝策略
     */
    private final DenyPolicy denyPolicy;

    /**
     * 存放任务的队列
     */
    private final LinkedList<Runnable> runnableList = new LinkedList<>();

    private final ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    /**
     * 当有新任务来的时候首先会
     *
     * @param runnable
     */
    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableList){
            if (runnableList.size() >= limit){
                // 无法容纳新服务，则执行拒绝策略
                denyPolicy.reject(runnable,threadPool);
            }else {
                runnableList.addLast(runnable);
                runnableList.notifyAll();
            }
        }
    }

    /**
     * 工作线程通过take方法获取Runnable
     *
     * @return
     */
    @Override
    public Runnable take() throws InterruptedException {
        synchronized (runnableList){
            while (runnableList.isEmpty()){
                try {
                    runnableList.wait();
                } catch (InterruptedException e) {
                    // 被中断时需要将该异常抛出。
                    throw e;
                }
            }
            return runnableList.removeFirst();
        }
    }

    /**
     * 获取任务队列中任务的数量
     *
     * @return
     */
    @Override
    public int size() {
        synchronized (runnableList){
            return runnableList.size();
        }
    }
}
