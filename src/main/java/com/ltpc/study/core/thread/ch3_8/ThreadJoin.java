package com.ltpc.study.core.thread.ch3_8;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created with IntelliJ IDEA.
 * User: liutong
 * Date: 2019-05-21
 * Time: 13:53
 * Description:
 **/
public class ThreadJoin {

    public static void main(String[] args) throws InterruptedException {
//        joinT1();
        joinT2();
    }

    /**
     * 验证线程join方法
     * @throws InterruptedException
     */
    public static void joinT1() throws InterruptedException {
        //定义两个线程
        List<Thread> threadList = IntStream.range(1,3).mapToObj(ThreadJoin::create).collect(Collectors.toList());

        //启动两个线程
        threadList.forEach(Thread::start);

        //执行线程join
        for (Thread thread: threadList){
            thread.join();
        }

        //main线程循环输出
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"#"+i);
            stopSleep();
        }
    }

    /**
     * 验证线程已经被停止是否仍然能join
     */
    public static void joinT2() throws InterruptedException {
        Thread thread = create(1);
        thread.start();

        TimeUnit.SECONDS.sleep(15);

        thread.join();
        System.out.println("The main thread is end");
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
