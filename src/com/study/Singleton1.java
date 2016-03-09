package com.study;

/**
 * ����ģʽ
 * ����һ���࣬���Ĺ��캯��Ϊprivate�ģ�����һ��static��private�ĸ�������������ʼ��ʱʵ������
 * ͨ��һ��public��getInstance������ȡ���������ã��̶��������еķ�����
 * @author Yuanxihua
 *
 */
public class Singleton1 {
	private Singleton1(){}
	//ע������privateֻ���ڲ�����
	private static Singleton1 instance = new Singleton1();
	public static Singleton1 getInstance(){
		return instance;
	}
}
