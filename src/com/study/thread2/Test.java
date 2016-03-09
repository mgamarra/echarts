package com.study.thread2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	public static void main(String[] args) {
		MyCount myCount = new MyCount("95599200901215522", 10000);
		ExecutorService pool = Executors.newFixedThreadPool(2);
		 Thread t1 = new SaveThread("����", myCount, 2000);
         Thread t2 = new SaveThread("����", myCount, 3600); 
         Thread t3 = new DrawThread("����", myCount, 2700); 
         Thread t4 = new SaveThread("����", myCount, 600); 
         Thread t5 = new DrawThread("��ţ", myCount, 1300); 
         Thread t6 = new DrawThread("����", myCount, 800); 
         //ִ�и����߳� 
         pool.execute(t1); 
         pool.execute(t2); 
         pool.execute(t3); 
         pool.execute(t4); 
         pool.execute(t5); 
         pool.execute(t6); 
         //�ر��̳߳� 
         pool.shutdown(); 
	}
}
