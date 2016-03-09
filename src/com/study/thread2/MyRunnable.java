package com.study.thread2;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;

public class MyRunnable implements Runnable{
	private static AtomicLong aLong = new AtomicLong(10000);//原子量，每个线程都可以自由操作
	private String name;//操作者
	private int x;//操作金额
	private Lock lock;
	
	public MyRunnable(String name, int x, Lock lock) {
		this.name = name;
		this.x = x;
		this.lock = lock;
	}

	public void run() {
		lock.lock();
		System.out.println(name+"执行了"+x+",当前余额："+aLong.addAndGet(x));
		lock.unlock();
	}

}
