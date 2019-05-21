package com.ltpc.study.core.thread.ch3_8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: liutong
 * Date: 2019-05-21
 * Time: 15:13
 * Description:
 **/
public class FightQueryTask extends Thread implements FightQuery {

    private final String origin;


    private final String destination;

    private final List<String> fightList = new ArrayList<>();

    public FightQueryTask(String airline,String origin,String destination){
        super("["+airline+"]");
        this.origin = origin;
        this.destination = destination;
    }


    @Override
    public List<String> get() {
        return this.fightList;
    }

    @Override
    public void run() {
        System.out.printf("%s-query form %s to %s\n", getName(), origin, destination);
        int randomVal = ThreadLocalRandom.current().nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(randomVal);
            this.fightList.add(getName() + "-" + randomVal);
            System.out.printf("The fight:%s list query successful\n",getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
