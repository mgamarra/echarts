package com.study;

/**
 * 单例模式
 * @author Yuanxihua
 *
 */
public class Singleton2 {
	private static Singleton2 instance = null;
	public static synchronized Singleton2 getInstance(){
		//不用每次都进行生成对象，只是第一次
		//使用时生成实例，提高了效率
		if(instance == null)
			instance = new Singleton2();
		return instance;
	}
}
