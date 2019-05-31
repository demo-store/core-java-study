package com.ltpc.study.core.thread.ch5.c04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: liutong
 * Date: 2019-05-30
 * Time: 16:51
 * Description:
 **/
public class BooleanLock implements Lock{

    private Thread currentThread;

    private boolean locker = false;

    private List<Thread> blockList = new ArrayList<>();

    /**
     * 改方法永远阻塞，但可中断，中断时抛出InterruptedException
     *
     * @throws InterruptedException
     */
    @Override
    public void lock() throws InterruptedException {
        synchronized (this){
            while (locker){
                final Thread tempThread = Thread.currentThread();
                try {
                    if (!blockList.contains(tempThread)){
                        blockList.add(tempThread);
                    }
                    this.wait();
                } catch (InterruptedException e) {
                    this.blockList.remove(tempThread);
                    throw e;
                }
            }
            currentThread = Thread.currentThread();
            this.locker = true;
            blockList.remove(Thread.currentThread());
        }
    }

    /**
     * 具有超时功能的锁，可中断，中断时抛出InterruptedException
     *
     * @param mills
     * @throws InterruptedException
     */
    @Override
    public void lock(long mills) throws InterruptedException {
        synchronized (this) {
            if (mills <= 0) {
                this.lock();
            }

            long remainingMills = mills;
            long endMills = System.currentTimeMillis() + mills;
            while (locker) {
                final Thread tempThread = Thread.currentThread();
                if (remainingMills <= 0){
                    this.blockList.remove(tempThread);
                    throw new InterruptedException("can not get the lock during " + mills + " ms.");
                }

                try {
                    if (!blockList.contains(tempThread)){
                        blockList.add(tempThread);
                    }
                    this.wait(mills);
                    remainingMills = System.currentTimeMillis() - endMills;
                } catch (InterruptedException e) {
                    this.blockList.remove(tempThread);
                    throw e;
                }
            }
            this.currentThread = Thread.currentThread();
            this.locker = true;
            this.blockList.remove(Thread.currentThread());
        }
    }

    /**
     * 用于锁的释放
     */
    @Override
    public void unlock() {
        synchronized (this){
            if (currentThread != Thread.currentThread()){
                return;
            }
            this.locker = false;
            Optional.of(Thread.currentThread().getName() + " release the lock.").isPresent();
            this.notifyAll();
        }
    }

    /**
     * 获取当前有哪些线程被阻塞
     *
     * @return
     */
    @Override
    public List<Thread> getBlockedThreads() {

        return Collections.unmodifiableList(blockList);
    }
}
