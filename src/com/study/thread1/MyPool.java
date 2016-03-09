package com.study.thread1;

import java.util.concurrent.Semaphore;

public class MyPool {
	private Semaphore sp;//池相关的信号量
	public MyPool(int size){
		this.sp = new Semaphore(size);
	}
	public Semaphore getSp() {
		return sp;
	}
	public void setSp(Semaphore sp) {
		this.sp = sp;
	}
	
}
