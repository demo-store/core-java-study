package com.ltpc.study.core.thread.ch8.ch2;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liutong
 * @date 2019-06-14 15:30
 * Description:
 **/
public interface RunnableQueue {

    /**
     * 当有新任务来的时候首先会
     * @param runnable
     */
    void offer(Runnable runnable);

    /**
     * 工作线程通过take方法获取Runnable
     * @return
     */
    Runnable take() throws InterruptedException;

    /**
     * 获取任务队列中任务的数量
     * @return
     */
    int size();
}
