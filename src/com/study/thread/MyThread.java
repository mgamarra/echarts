package com.study.thread;

public class MyThread extends Thread{
	private User user;
	private int y = 0;
	
	public MyThread(String name, User user, int y) {
		super(name);
		this.user = user;
		this.y = y;
	}

	public void run() {
		user.oper(y);
	}								
	
}
