package com.study;

/**
 * ����ģʽ
 * @author Yuanxihua
 *
 */
public class Singleton2 {
	private static Singleton2 instance = null;
	public static synchronized Singleton2 getInstance(){
		//����ÿ�ζ��������ɶ���ֻ�ǵ�һ��
		//ʹ��ʱ����ʵ���������Ч��
		if(instance == null)
			instance = new Singleton2();
		return instance;
	}
}
