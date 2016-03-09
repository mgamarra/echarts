package com.study.thread1;

public class MyThread extends Thread {
	private String threadname;//�̵߳�����
	private MyPool pool;      //�Զ����
	private int x;			  //�����ź����Ĵ�С
	
	public MyThread(String threadname, MyPool pool, int x) {
		this.threadname = threadname;
		this.pool = pool;
		this.x = x;
	}

	public void run() {
		try {
			//�Ӵ��ź�����ȡ������Ŀ�����
			pool.getSp().acquire(x);
			//todo��Ҳ����������������ӵ�ҵ��
			System.out.println(threadname+"�ɹ���ȡ��"+x+"�����!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			//�ͷŸ�����Ŀ����ɣ����䷵�ص��ź���
			pool.getSp().release(x);
			System.out.println(threadname+"�ͷ���"+x+"�����!");
		}
	}

}
