package com.ltpc.study.core.thread.ch1;

/**
 * Created with IntelliJ IDEA.
 * User: liutong
 * Date: 2019-05-17
 * Time: 11:00
 * Description:
 **/
public class ThreadConstruction {

    public static void main(String[] args) {
        Thread t1 = new Thread("t1");
        ThreadGroup group = new ThreadGroup("TestGroup");

        Thread t2 = new Thread(group,"t2");
        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();

        System.out.println("Main thread belong group:"+mainThreadGroup.getName());
        System.out.println("t1 and main belong the same group:"+(t1.getThreadGroup() == mainThreadGroup));
        System.out.println("t2 thread group not belong main group:"+(t2.getThreadGroup() == mainThreadGroup));
        System.out.println("t2 thread group belong main testGroup:"+(group == t2.getThreadGroup()));
    }
}
