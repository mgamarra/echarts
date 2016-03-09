package com.study.thread2;

/**
 * 存款线程类
 * @author Yuanxihua
 *
 */
public class SaveThread extends Thread{
	private String name;//操作人
	private MyCount myCount;//账号
	private int x;//存款金额
	public SaveThread(String name, MyCount myCount, int x) {
		this.name = name;
		this.myCount = myCount;
		this.x = x;
	}
	public void run(){
		myCount.saving(x, name);
	}
}
