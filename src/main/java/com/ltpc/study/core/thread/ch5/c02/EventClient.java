package com.ltpc.study.core.thread.ch5.c02;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: liutong
 * Date: 2019-05-23
 * Time: 09:53
 * Description:
 **/
public class EventClient {

    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();

        //产生事件
        new Thread(()->{
            while (true){
                eventQueue.offer(new EventQueue.Event());
            }
        },"Producer").start();

        //消费事件
        new Thread(()->{
            while (true){
                eventQueue.take();
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Consumer").start();
    }
}
