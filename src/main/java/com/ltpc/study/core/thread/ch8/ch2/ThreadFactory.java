package com.ltpc.study.core.thread.ch8.ch2;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liutong
 * @date 2019-06-14 15:38
 * Description:
 **/
@FunctionalInterface
public interface ThreadFactory {

    Thread createThread(Runnable runnable);
}
