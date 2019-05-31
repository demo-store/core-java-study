package com.ltpc.study.core.thread.ch5.c04;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

/**
 * Created with IntelliJ IDEA.
 * User: liutong
 * Date: 2019-05-30
 * Time: 17:34
 * Description:
 **/
public class BooleanLockTest {
    private Lock lock = new BooleanLock();

    public static void main(String[] args) throws InterruptedException {
        BooleanLockTest lockTest = new BooleanLockTest();

        // 1. 多个线程通过lock争抢锁
//        IntStream.range(0, 10).mapToObj(i->new Thread(lockTest::syncMethod)).forEach(Thread::start);

        // 2. 可中断被阻塞线程  start
//        new Thread(lockTest::syncMethod,"T1").start();
//        TimeUnit.MILLISECONDS.sleep(2);
//        Thread t2 = new Thread(lockTest::syncMethod, "T2");
//        t2.start();
//        TimeUnit.MILLISECONDS.sleep(10);
//        t2.interrupt();
        // 2. 可中断被阻塞线程  end

        // 3. 阻塞线程可超时 start
        new Thread(lockTest::syncMethod,"T1").start();
        TimeUnit.MILLISECONDS.sleep(2);
        Thread t2 = new Thread(lockTest::syncMethodTimeoutable,"T2");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(10);
        // 3. 阻塞线程可超时 end
    }

    public void syncMethod() {
        System.out.println("start syncMethod");
        try {
            lock.lock();
            System.out.println(LocalDateTime.now()+": "+Thread.currentThread() + " get the lock by syncMethod.");
            int randomInt = ThreadLocalRandom.current().nextInt(10);
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void syncMethodTimeoutable(){
        System.out.println("start syncMethodTimeoutable.");
        try {
            lock.lock(1000);
            System.out.println(LocalDateTime.now()+": "+Thread.currentThread() + " get the lock by syncMethodTimeoutable.");
            int randomInt = ThreadLocalRandom.current().nextInt(10);
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
