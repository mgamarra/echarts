package com.study.thread1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Test1 {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue bqueue = new ArrayBlockingQueue(20);
		for (int i = 0; i < 30; i++) {
			//将指定元素添加到此队列中，如果没有可用空间，将一直等待（如果有必要）。 
			bqueue.put(i);
			System.out.println("向阻塞列队中添加了元素："+i);
		}
		System.out.println("程序到此运行结束，即将退出----"); 
	}
}
