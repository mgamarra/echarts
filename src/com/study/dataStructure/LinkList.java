package com.study.dataStructure;

/**
 * 定义一个链表主类，并且定义各种对链表操作的方法
 * @author Yuanxihua
 *
 */
public class LinkList {
	public LNode head;//定义一个头节点
	
	/**
	 * 定义一个创建链表的方法
	 * 该方法称之为：尾插法：新产生的节点从尾部插入链表
	 * @param a
	 */
	public void createlink(int[] a){
		LNode pnew;//定义pnew表示新产生的节点
		LNode ptail = new LNode();//为尾部节点分配内存
		head = ptail;//初始时是头部节点与尾部节点相等
		for (int i = 0; i < a.length; i++) {
			pnew = new LNode();//为新产生的节点分配内存
			pnew.setData(a[i]);//传递data值
			ptail.setNext(pnew);//把新产生的节点设置为ptail的后继节点
			pnew.setNext(null);//把新产生的节点的后继节点设为空
			ptail = pnew;//移动 ptail节点的位置指向尾部
		}
	}
	
	/**
	 * 定义判断链表中元素是否存在的方法
	 * @param value
	 */
	public void seachlink(int value){
		LNode ptr;
		ptr = head.getNext();
		while(ptr != null){//在节点非空的情况下寻找匹配的值
			if(value == ptr.getData()){
				System.out.println("找到数据："+ptr.getData());
				break;//提出循环
			}else{
				ptr = ptr.getNext();
			}
		}
		if(ptr == null){//链表遍历完毕，没有找到时
			System.out.println("链表中没有要查找数据");
		}
	}
	
	/**
	 * 定义一个删除节点的方法
	 * @param value
	 */
	public void deletelink(int value){
		LNode ptr;
		LNode p;
		p = head;
		ptr = head.getNext();
		while(ptr != null){
			if(value == ptr.getData()){//判断链表中的当前值是否是要删除的节点
				p.setNext(ptr.getNext());
				System.out.println("删除数据"+value+"成功");
				break;
			}else{
				p = ptr;//p指向ptr位置
				ptr = ptr.getNext();//ptr指向其直接后继位置
			}
		}
		if(ptr == null){
			System.out.println("链表中没有要删除的数据！");
		}
	}
	
	/**
	 * 定义插入节点的方法
	 * @param pos
	 * @param value
	 */
	public void insertlink(int pos, int value){//两个参数，一个表示插入的位置，另一个表示插入的值
		LNode ptr;
		LNode pnew;//实例化新节点
		ptr = head.getNext();
		while(ptr != null){
			if(pos == ptr.getData()){
				pnew = new LNode();
				pnew.setData(value);
				pnew.setNext(ptr.getNext());
				ptr.setNext(pnew);
				System.out.println("插入数据"+value+"成功！");
				break;
			}else{
				ptr = ptr.getNext();
			}
		}
		if(ptr == null){
			System.out.println("插入数据失败！");
		}
	}
	
	/**
	 * 定义一个输出链表内容方法
	 */
	public void printlink(){
		LNode ptr;//实例化一个节点
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
