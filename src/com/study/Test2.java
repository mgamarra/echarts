package com.study;

import java.util.Arrays;

/**
 * ��������n�����֣��Զ��ţ��ֿ���Ȼ���ѡ�������߽�������
 * @author Yuanxihua
 *
 */
public class Test2 {
	public static void main(String[] args) {
		String str = "5,4,6,7,3,2,1,8,9";
		String[] result = str.split(",");
		int[] it = new int[result.length];
		for (int i = 0; i < it.length; i++) {
			it[i] = Integer.parseInt(result[i]);
		}
		Arrays.sort(it);
		//asc   
		for (int i = 0; i < it.length; i++) {
			System.out.print(it[i]+" ");
		}
		System.out.println();
		//desc
		for (int i = (result.length - 1); i >= 0; i--) {
			System.out.print(it[i]+" ");
		}
	}
	
}
