package com.study.thread2;

/**
 * ����߳���
 * @author Yuanxihua
 *
 */
public class SaveThread extends Thread{
	private String name;//������
	private MyCount myCount;//�˺�
	private int x;//�����
	public SaveThread(String name, MyCount myCount, int x) {
		this.name = name;
		this.myCount = myCount;
		this.x = x;
	}
	public void run(){
		myCount.saving(x, name);
	}
}
