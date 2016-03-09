package com.study;

/**
 * 单例模式
 * 定义一个类，它的构造函数为private的，它有一个static的private的该类变量，在类初始化时实例化，
 * 通过一个public的getInstance方法获取对它的引用，继而调用其中的方法。
 * @author Yuanxihua
 *
 */
public class Singleton1 {
	private Singleton1(){}
	//注意这是private只供内部调用
	private static Singleton1 instance = new Singleton1();
	public static Singleton1 getInstance(){
		return instance;
	}
}
