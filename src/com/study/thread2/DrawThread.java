package com.study.thread2;

/**
 * ȡ���߳���
 * @author Yuanxihua
 *
 */
public class DrawThread extends Thread{
	private String name;//������
	private MyCount myCount;//�˺�
	private int x;//�����
	
	public DrawThread(String name, MyCount myCount, int x) {
		this.name = name;
		this.myCount = myCount;
		this.x = x;
	}


	public void run(){
		myCount.drawing(x, name);
	}
}
