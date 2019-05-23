package com.ltpc.study.core.thread.ch5.c02;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: liutong
 * Date: 2019-05-22
 * Time: 17:51
 * Description:
 **/
public class EventQueue {
    private final int max;

    private static final int DEFAULT_MAX_EVENT = 10;

    private final LinkedList<Event> eventQueue = new LinkedList<>();

    static class Event{

    }

    public EventQueue(){
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueue(int max){
        this.max = max;
    }

    public void offer(Event event){
        synchronized (eventQueue){
            if(eventQueue.size() > max){
                try {
                    console(" the queue is full");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            console(" the new event is submitted");
            eventQueue.addLast(event);
            eventQueue.notify();
        }
    }

    public Event take(){
        synchronized (eventQueue){
            if (eventQueue.isEmpty()){
                try {
                    console(" the queue is empty");
                    eventQueue.wait();
                } catch (InterruptedException e){

                }
            }

            Event event = eventQueue.removeFirst();
            this.eventQueue.notify();
            console(" the event " + event + " is handled.");
            return event;
        }
    }

    private void console(String message){
        System.out.printf("%s:%s\n",Thread.currentThread().getName(), message);
    }
}
