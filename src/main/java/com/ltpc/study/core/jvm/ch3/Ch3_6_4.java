package com.ltpc.study.core.jvm.ch3;

/**
 * 
 * @author jackieliu
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * -XX:MaxTenuringThreshold=1
 * -XX:+PrintTenuringDistribution
 * -XX:+UseSerialGC 使用Serial收集器
 */
public class Ch3_6_4 {
	private static final int _1MB = 1024*1024;

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		byte[] allocation1,allocation2,allocation3,allocation4; 
		allocation1 = new byte[_1MB/4];
		allocation2 = new byte[_1MB/4];
		allocation3 = new byte[_1MB * 4];
		System.out.println("3---4");
		allocation4 = new byte[_1MB * 4];
		allocation4 = null;
		allocation4 = new byte[_1MB * 4];
	}

}
