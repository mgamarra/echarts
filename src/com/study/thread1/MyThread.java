package com.study.thread1;

public class MyThread extends Thread {
	private String threadname;//线程的名称
	private MyPool pool;      //自定义池
	private int x;			  //申请信号量的大小
	
	public MyThread(String threadname, MyPool pool, int x) {
		this.threadname = threadname;
		this.pool = pool;
		this.x = x;
	}

	public void run() {
		try {
			//从此信号量获取给定数目的许可
			pool.getSp().acquire(x);
			//todo：也许这里可以做更复杂的业务
			System.out.println(threadname+"成功获取了"+x+"个许可!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			//释放给定数目的许可，将其返回到信号量
			pool.getSp().release(x);
			System.out.println(threadname+"释放了"+x+"个许可!");
		}
	}

}
