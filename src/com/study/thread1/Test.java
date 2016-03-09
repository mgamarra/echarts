package com.study.thread1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	public static void main(String[] args) {
		MyPool myPool = new MyPool(20);
		//�������ӳ�
		ExecutorService threadPool = Executors.newFixedThreadPool(2);
		MyThread t1 = new MyThread("����A",myPool,3);
		MyThread t2 = new MyThread("����B",myPool,12);
		MyThread t3 = new MyThread("����C",myPool,7);
		//���߳���ִ������
		threadPool.execute(t1);
		threadPool.execute(t2);
		threadPool.execute(t3);
		//�رճ�
		threadPool.shutdown();
	}
}
