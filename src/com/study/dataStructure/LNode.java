package com.study.dataStructure;

/**
 * �ȶ���һ��Node�������洢�ڵ��ֵ���ָ����
 * ����ǰ�ڵ��е�ֵ�ͺ���ڵ�ķ���
 * @author Yuanxihua
 *
 */
public class LNode {
	private int data;
	private LNode next;//�����StringӦ�ñȽ����Ƶ��÷�������������ʾ�������ͣ���ʾnext����������Ҳ�ǽڵ�
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
