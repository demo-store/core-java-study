package com.ltpc.study.core.thread.ch4.c03;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: liutong
 * Date: 2019-05-22
 * Time: 13:53
 * Description:
 **/
public class Mutex {
    private static final Object MUTEX = new Object();

    public void accessResource(){
        synchronized (MUTEX){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Mutex mutex = new Mutex();
        for (int i = 0; i < 5; i++) {
            new Thread(mutex::accessResource).start();
        }
    }
}
