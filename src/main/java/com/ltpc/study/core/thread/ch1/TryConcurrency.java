package com.ltpc.study.core.thread.ch1;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: liutong
 * Date: 2018/8/28
 * Time: 下午5:58
 * Description:
 **/
public class TryConcurrency {

    public static void main(String[] args) {
        new Thread(TryConcurrency::enjoyMusic).start();
        browseNews();
    }

    private static void browseNews(){
        while (true){
            System.out.println(LocalDateTime.now()+" Un-huh,the good news");
            sleep(1);
        }
    }

    private static void enjoyMusic(){
        while (true){
            System.out.println(LocalDateTime.now()+" Uh-huh,the nice music");
            sleep(1);
        }
    }

    private static void sleep(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
