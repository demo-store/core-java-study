package com.ltpc.study.core.thread.ch5.c02;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: liutong
 * Date: 2019-05-23
 * Time: 10:05
 * Description:
 **/
public class EventDemo {

    public synchronized void demo1(){
        while (true){
            System.out.printf("%s start ", LocalTime.now().toString());
        }
    }

    public static void main(String[] args) {
        EventDemo demo = new EventDemo();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            demo.demo1();
            try {
                demo.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        demo.notify();
    }
}
