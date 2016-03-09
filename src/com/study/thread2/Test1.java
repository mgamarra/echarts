package com.study.thread2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test1 {
	public static void main(String[] args) {
		 ExecutorService pool = Executors.newFixedThreadPool(2); 
         Lock lock = new ReentrantLock(false); 
         Runnable t1 = new MyRunnable("����", 2000,lock); 
         Runnable t2 = new MyRunnable("����", 3600,lock); 
         Runnable t3 = new MyRunnable("����", 2700,lock); 
         Runnable t4 = new MyRunnable("����", 600,lock); 
         Runnable t5 = new MyRunnable("��ţ", 1300,lock); 
         Runnable t6 = new MyRunnable("����", 800,lock); 
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
