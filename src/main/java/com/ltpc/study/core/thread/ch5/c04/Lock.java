package com.ltpc.study.core.thread.ch5.c04;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liutong
 * Date: 2019-05-30
 * Time: 16:47
 * Description:
 **/
public interface Lock {
    /**
     * 改方法永远阻塞，但可中断，中断时抛出InterruptedException
     * @throws InterruptedException
     */
    void lock() throws InterruptedException;

    /**
     * 具有超时功能的锁，可中断，中断时抛出InterruptedException
     * @param mills
     * @throws InterruptedException
     */
    void lock(long mills) throws InterruptedException;

    /**
     * 用于锁的释放
     */
    void unlock();

    /**
     * 获取当前有哪些线程被阻塞
     * @return
     */
    List<Thread> getBlockedThreads();
}
