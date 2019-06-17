package com.ltpc.study.core.thread.ch8.ch3;

import com.ltpc.study.core.thread.ch8.ch2.BasicThreadPool;
import com.ltpc.study.core.thread.ch8.ch2.ThreadPool;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liutong
 * @date 2019-06-17 10:21
 * Description:
 **/
public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        // 定义线程池，初始化线程2，核心线程数为4，最大线程数为6，任务队列最多允许1000个线程
        final ThreadPool threadPool = new BasicThreadPool(2,6,4,1000);
        // 定义20个线程，并提交给线程池
        for (int i = 0; i < 20; i++) {
            threadPool.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " is running and done.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        // 不断输出线程池信息
        while (true){
            System.out.println("activeCount:" + threadPool.getActiveCount());
            System.out.println("queueSize:" + threadPool.getQueueSize());
            System.out.println("coreSize:" + threadPool.getCoreSize());
            System.out.println("maxSize:" + threadPool.getMaxSize());
            System.out.println("==========================================");
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
