package com.ltpc.study.core.thread.ch1;

public class MyRunnable implements Runnable {
	private int ticket = 5;
	@Override
	public void run() {
		for(int i=0;i<10 && ticket>0;i++){
			System.out.println("ticket = "+ ticket--);
		}
	}

}