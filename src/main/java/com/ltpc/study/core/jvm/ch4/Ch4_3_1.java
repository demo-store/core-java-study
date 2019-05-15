package com.ltpc.study.core.jvm.ch4;

import java.util.ArrayList;
import java.util.List;

public class Ch4_3_1 {

	static class OOMObject{
		public byte[] placeholder = new byte[64*1024];
	}
	
	public static void fillHeap(int num) throws InterruptedException{
		List<OOMObject> list = new ArrayList<OOMObject>();
		for (int i = 0;i<num;i++){
			//稍作延迟
		}
	}
	
	public static void main(String[] args) {
		
	}

}
