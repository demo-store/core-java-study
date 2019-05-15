package com.ltpc.study.core.jvm.ch4;

/**
 * 
 * @author jackieliu
 * Q:
 * 加入参数-XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly运行时，可能会报如下错误：
 * Could not load hsdis-amd64.dylib; library not loadable; PrintAssembly is disabled
 * A:
 * 是因为hsdis-amd64插件没有放入指定路径，导致运行时无法加载。
 * 一定要将hsdis-amd64.dylib放在$JAVA_PATH/jre/lib/server/中，与libjvm.dylib同目录（mac系统，如果是linux放在与libjvm.so同目录）
 * http://qkxue.net/info/177863/Java-java-02-015
 */
public class Ch4_2_7 {
	int a = 1;
	static int b = 2;
	
	public int sum(int c){
		return a + b + c;
	}
	
	public static void main(String[] args) {
		new Ch4_2_7().sum(3);
	}
}
