package com.ltpc.study.core.thread.ch10.ch1;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liutong
 * @date 2019-06-18 11:29
 * Description:
 **/
public class ApplicationClassLoader {

    public static void main(String[] args) {
        System.out.println("classLoad: "+ ApplicationClassLoader.class.getClassLoader());
        String dirsStr = System.getProperty("java.class.path");
        String[] dirArray = dirsStr.split(":");
        for (int i = 0; i < dirArray.length; i++) {
            System.out.println(dirArray[i]);
        }
    }
}
