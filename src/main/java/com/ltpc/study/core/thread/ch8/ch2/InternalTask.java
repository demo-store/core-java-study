package com.ltpc.study.core.thread.ch8.ch2;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liutong
 * @date 2019-06-14 15:50
 * Description:
 **/
public class InternalTask implements Runnable {
    private final RunnableQueue runnableQueue;

    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }

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
        while (running && !Thread.currentThread().isInterrupted()){
            try {
                Runnable take = runnableQueue.take();
                take.run();
            }catch (Exception e){
                running = false;
                break;
            }
        }
    }

    public void stop(){
        this.running = false;
    }
}
