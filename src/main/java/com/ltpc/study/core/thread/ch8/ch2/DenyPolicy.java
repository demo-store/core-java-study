package com.ltpc.study.core.thread.ch8.ch2;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liutong
 * @date 2019-06-14 15:39
 * Description:
 **/
@FunctionalInterface
public interface DenyPolicy {

    void reject(Runnable runnable,ThreadPool threadPool);

    /**
     * 该拒绝策略会直接将任务丢弃
     */
    class DiscardDenyPolicy implements DenyPolicy{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            // TODO...
        }
    }

    /**
     * 该拒绝策略会向任务提交者抛出异常
     */
    class AbortDenyPolicy implements  DenyPolicy{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            // TODO... 补充异常
//            throw new RunnableDenyException("The runnable " + runnable + " will be abort.");
        }
    }

    /**
     * 该拒绝策略会使任务在提交者线程中运行。
     */
    class RunnerDenyPolicy implements DenyPolicy{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            if(!threadPool.isShutdown()){
                runnable.run();
            }
        }
    }


}
