package com.ltpc.study.core.thread.ch3_8;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: liutong
 * Date: 2019-05-21
 * Time: 14:23
 * Description:
 **/
public class ThreadJoinInterrupt {

    public static void main(String[] args)  {
        Thread mainThread = Thread.currentThread();
        Thread thread1 = new Thread(()->{
            for (int i = 0;i < 10;i++){
                System.out.println(Thread.currentThread().getName()+"#"+i);
                stopSleep();
            }
        },"lt1");



        Thread thread2 = new Thread(()->{
            for (int i = 0;i < 5;i++){
                System.out.println(Thread.currentThread().getName()+"#"+i);
                stopSleep();
            }
            System.out.println("main interrupt start flat:"+mainThread.isInterrupted());
            mainThread.interrupt();
            System.out.println("main interrupt edn flat:"+mainThread.isInterrupted());
        },"lt2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main thread end");

    }

    /**
     * 构造简单线程，每个线程循环输出。
     * @param seq
     * @return
     */
    private static Thread create(int seq){
        return new Thread(()->{
            for (int i = 0;i < 10;i++){
                System.out.println(Thread.currentThread().getName()+"#"+i);
                stopSleep();
            }
        }, String.valueOf(seq));
    }

    private static void stopSleep(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
