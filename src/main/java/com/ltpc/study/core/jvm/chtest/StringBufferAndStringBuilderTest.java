package com.ltpc.study.core.jvm.chtest;

import java.util.concurrent.CountDownLatch;

/**
 * Created by liutong on 2017/1/11.
 */
public class StringBufferAndStringBuilderTest {
    public static final int THREAD_NUM = 1000;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String strToReverse = "AAAABBBB";

        StringBuffer stringBuffer = new StringBuffer(strToReverse);
        StringBuilder stringBuilder = new StringBuilder(strToReverse);
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);
        CountDownLatch countDownLatch1 = new CountDownLatch(THREAD_NUM);

    }
}
