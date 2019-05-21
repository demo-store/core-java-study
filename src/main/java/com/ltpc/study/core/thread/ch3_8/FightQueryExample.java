package com.ltpc.study.core.thread.ch3_8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: liutong
 * Date: 2019-05-21
 * Time: 15:33
 * Description:
 **/
public class FightQueryExample {

    public static List<String> fightCompay = Arrays.asList("CSA","CEA","HNA");

    public static void main(String[] args) {
        List<String> result = search("BJ","SH");
        System.out.println("=============result=============");
        result.forEach(System.out::println);
    }

    private static List<String> search(String original,String dest){
        final List<String> result = new ArrayList<>();
        List<FightQueryTask> taskList = fightCompay.stream().map(f->createSearchTash(f,original,dest)).collect(Collectors.toList());

        taskList.forEach(Thread::start);
        taskList.forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        taskList.stream().map(FightQueryTask::get).forEach(result::addAll);

        return result;
    }

    private static FightQueryTask createSearchTash(String fight,String original,String dest){
        return new FightQueryTask(fight,original,dest);
    }
}
