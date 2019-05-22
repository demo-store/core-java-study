package com.ltpc.study.core.thread.ch3.c07;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: liutong
 * Date: 2019-05-20
 * Time: 17:30
 * Description:
 **/
public class ThreadIsInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            while (true){
                //do nothing
            }
        });

        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.println("the interrupt is "+thread.isInterrupted());
        thread.interrupt();
        System.out.println("the interrupt is "+thread.isInterrupted());
    }
}
