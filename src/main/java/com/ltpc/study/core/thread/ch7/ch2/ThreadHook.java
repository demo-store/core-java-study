package com.ltpc.study.core.thread.ch7.ch2;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: liutong
 * Date: 2019-05-31
 * Time: 17:15
 * Description:
 **/
public class ThreadHook {

    public static void main(String[] args) {
        // 为应用程序注入钩子
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("the hook thread 1 is running.");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("the hook thread 1 is exit.");
        }));

        // 为应用程序可注册多个钩子
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("the hook thread 2 is running.");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("the hook thread 2 is exit.");
        }));

        System.out.println("The program will is stopping.");
    }
}
