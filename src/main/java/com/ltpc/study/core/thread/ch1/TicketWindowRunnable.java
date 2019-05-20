package com.ltpc.study.core.thread.ch1;

/**
 * Created with IntelliJ IDEA.
 * User: liutong
 * Date: 2019-05-17
 * Time: 10:29
 * Description:
 **/
public class TicketWindowRunnable implements Runnable {
    private int index = 1;

    private static final int MAX = 50;
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
        while (index <= MAX){
            System.out.println(Thread.currentThread()+"，当前号码："+(index++));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TicketWindowRunnable task = new TicketWindowRunnable();

        Thread thread1 = new Thread(task,"一号出号机");
        Thread thread2 = new Thread(task,"二号出号机");
        Thread thread3 = new Thread(task,"三号出号机");
        Thread thread4 = new Thread(task,"四号出号机");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
