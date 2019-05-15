package com.ltpc.study.core.jvm.ch3;

/**
 * 
 * @author jackieliu
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * -XX:PretenureSizeThreshold=3145728
 * -XX:+UseSerialGC 使用Serial收集器
 */
public class Ch3_6_2 {
	private static final int _1MB = 1024*1024;

	public static void main(String[] args) {
		byte[] allocation = new byte[4*_1MB]; //直接分配在老年代中
	}

}
