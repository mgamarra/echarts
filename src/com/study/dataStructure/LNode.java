package com.study.dataStructure;

/**
 * 先定义一个Node类用来存储节点的值域和指针域
 * 即当前节点中的值和后面节点的方法
 * @author Yuanxihua
 *
 */
public class LNode {
	private int data;
	private LNode next;//这个和String应该比较相似的用法，类名用来表示数据类型，表示next的数据类型也是节点
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public LNode getNext() {
		return next;
	}
	public void setNext(LNode next) {
		this.next = next;
	}
	
}
