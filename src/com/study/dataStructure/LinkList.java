package com.study.dataStructure;

/**
 * ����һ���������࣬���Ҷ�����ֶ���������ķ���
 * @author Yuanxihua
 *
 */
public class LinkList {
	public LNode head;//����һ��ͷ�ڵ�
	
	/**
	 * ����һ����������ķ���
	 * �÷�����֮Ϊ��β�巨���²����Ľڵ��β����������
	 * @param a
	 */
	public void createlink(int[] a){
		LNode pnew;//����pnew��ʾ�²����Ľڵ�
		LNode ptail = new LNode();//Ϊβ���ڵ�����ڴ�
		head = ptail;//��ʼʱ��ͷ���ڵ���β���ڵ����
		for (int i = 0; i < a.length; i++) {
			pnew = new LNode();//Ϊ�²����Ľڵ�����ڴ�
			pnew.setData(a[i]);//����dataֵ
			ptail.setNext(pnew);//���²����Ľڵ�����Ϊptail�ĺ�̽ڵ�
			pnew.setNext(null);//���²����Ľڵ�ĺ�̽ڵ���Ϊ��
			ptail = pnew;//�ƶ� ptail�ڵ��λ��ָ��β��
		}
	}
	
	/**
	 * �����ж�������Ԫ���Ƿ���ڵķ���
	 * @param value
	 */
	public void seachlink(int value){
		LNode ptr;
		ptr = head.getNext();
		while(ptr != null){//�ڽڵ�ǿյ������Ѱ��ƥ���ֵ
			if(value == ptr.getData()){
				System.out.println("�ҵ����ݣ�"+ptr.getData());
				break;//���ѭ��
			}else{
				ptr = ptr.getNext();
			}
		}
		if(ptr == null){//���������ϣ�û���ҵ�ʱ
			System.out.println("������û��Ҫ��������");
		}
	}
	
	/**
	 * ����һ��ɾ���ڵ�ķ���
	 * @param value
	 */
	public void deletelink(int value){
		LNode ptr;
		LNode p;
		p = head;
		ptr = head.getNext();
		while(ptr != null){
			if(value == ptr.getData()){//�ж������еĵ�ǰֵ�Ƿ���Ҫɾ���Ľڵ�
				p.setNext(ptr.getNext());
				System.out.println("ɾ������"+value+"�ɹ�");
				break;
			}else{
				p = ptr;//pָ��ptrλ��
				ptr = ptr.getNext();//ptrָ����ֱ�Ӻ��λ��
			}
		}
		if(ptr == null){
			System.out.println("������û��Ҫɾ�������ݣ�");
		}
	}
	
	/**
	 * �������ڵ�ķ���
	 * @param pos
	 * @param value
	 */
	public void insertlink(int pos, int value){//����������һ����ʾ�����λ�ã���һ����ʾ�����ֵ
		LNode ptr;
		LNode pnew;//ʵ�����½ڵ�
		ptr = head.getNext();
		while(ptr != null){
			if(pos == ptr.getData()){
				pnew = new LNode();
				pnew.setData(value);
				pnew.setNext(ptr.getNext());
				ptr.setNext(pnew);
				System.out.println("��������"+value+"�ɹ���");
				break;
			}else{
				ptr = ptr.getNext();
			}
		}
		if(ptr == null){
			System.out.println("��������ʧ�ܣ�");
		}
	}
	
	/**
	 * ����һ������������ݷ���
	 */
	public void printlink(){
		LNode ptr;//ʵ����һ���ڵ�
		ptr = head.getNext();
		while(ptr != null){
			System.out.print(ptr.getData()+"->");
			ptr = ptr.getNext();
		}
		System.out.println(" NULL ");
	}
	public static void main(String[] args) {
		LinkList link = new LinkList();
		int[] a = {1,2,3,4,5};
		link.createlink(a);
		//link.deletelink(3);
		link.insertlink(4, 6);
		link.printlink();
		
		link.seachlink(2);
	}
	
}
