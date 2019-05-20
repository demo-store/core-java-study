package com.ltpc.study.core.thread.ch1;

/**
 * Created with IntelliJ IDEA.
 * User: liutong
 * Date: 2019-05-17
 * Time: 10:04
 * Description:
 **/
public class TicketWindow extends Thread {
    /**
     * 出号机名称
     */
    private final String name;

    private static final int MAX = 50;

    public static int index = 1;

    /**
     * Allocates a new {@code Thread} object. This constructor has the same
     * effect as {@linkplain #Thread(ThreadGroup, Runnable, String) Thread}
     * {@code (null, null, name)}.
     *
     * @param name the name of the new thread
     */
    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index <= MAX){
            System.out.println("柜台："+name+"，当前号码："+(index++));
        }
    }

    public static void main(String[] args) {
        TicketWindow ticketWindow1 = new TicketWindow("一号出号机");
        ticketWindow1.start();

        TicketWindow ticketWindow2 = new TicketWindow("二号出号机");
        ticketWindow2.start();

        TicketWindow ticketWindow3 = new TicketWindow("三号出号机");
        ticketWindow3.start();

        TicketWindow ticketWindow4 = new TicketWindow("四号出号机");
        ticketWindow4.start();
    }
}
