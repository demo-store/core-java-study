package com.ltpc.study.core.thread.ch3.c07;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: liutong
 * Date: 2019-05-20
 * Time: 17:20
 * Description:
 **/
public class ThreadInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("oh, I am interrupter.");
            }
        });

        thread.start();

        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();
    }
}
