package com.ltpc.study.core.thread.ch10.ch1;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liutong
 * @date 2019-06-18 11:18
 * Description:
 **/
public class ExtClassLoader {

    public static void main(String[] args) {
        String dirsStr = System.getProperty("java.ext.dirs");
        String[] dirArray = dirsStr.split(":");
        for (int i = 0; i < dirArray.length; i++) {
            System.out.println(dirArray[i]);
        }
    }
}
