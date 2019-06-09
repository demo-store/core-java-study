package com.ltpc.study.core.thread.ch7.ch1;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: liutong
 * Date: 2019-05-31
 * Time: 16:43
 * Description:
 **/
public class CaptureThreadException {


    public static void main(String[] args) {
        // 设置默认异常处理。
        Thread.setDefaultUncaughtExceptionHandler((t,e)->{
            System.out.println(t.getName()+" occur exception");
            e.printStackTrace();
        });

        final Thread thread = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 用于抛出异常
            System.out.println(1/0);
        },"Test-thread");

        thread.start();
    }
}
