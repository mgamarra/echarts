package com.study.thread1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Test1 {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue bqueue = new ArrayBlockingQueue(20);
		for (int i = 0; i < 30; i++) {
			//��ָ��Ԫ����ӵ��˶����У����û�п��ÿռ䣬��һֱ�ȴ�������б�Ҫ���� 
			bqueue.put(i);
			System.out.println("�������ж��������Ԫ�أ�"+i);
		}
		System.out.println("���򵽴����н����������˳�----"); 
	}
}
