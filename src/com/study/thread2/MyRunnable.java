package com.study.thread2;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;

public class MyRunnable implements Runnable{
	private static AtomicLong aLong = new AtomicLong(10000);//ԭ������ÿ���̶߳��������ɲ���
	private String name;//������
	private int x;//�������
	private Lock lock;
	
	public MyRunnable(String name, int x, Lock lock) {
		this.name = name;
		this.x = x;
		this.lock = lock;
	}

	public void run() {
		lock.lock();
		System.out.println(name+"ִ����"+x+",��ǰ��"+aLong.addAndGet(x));
		lock.unlock();
	}

}
