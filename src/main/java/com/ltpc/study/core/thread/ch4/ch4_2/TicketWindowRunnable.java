package com.ltpc.study.core.thread.ch4.ch4_2;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: liutong
 * Date: 2019-05-17
 * Time: 10:29
 * Description:
 **/
public class TicketWindowRunnable implements Runnable {
    private int index = 1;

    private static final int MAX = 500;

    private static final Object MUTEX = new Object();
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
            while (index <= MAX) {
                showNum();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }

    private synchronized void showNum(){
        System.out.println(Thread.currentThread().getName() + "，当前号码：" + (index++));
    }

    public static void main(String[] args) {
        TicketWindowRunnable task = new TicketWindowRunnable();

        Thread windowsThread1 = new Thread(task,"一号出号机");
        Thread windowsThread2 = new Thread(task,"二号出号机");
        Thread windowsThread3 = new Thread(task,"三号出号机");
        Thread windowsThread4 = new Thread(task,"四号出号机");

        windowsThread1.start();
        windowsThread2.start();

        windowsThread3.start();
        windowsThread4.start();
    }
}
