package com.study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CopyArray {
	public static void main(String[] args) {
		String[] srcArray = {null,"2","3","4",null,"6","7","8",null,"10"};
		CopyArray array = new CopyArray();
//		array.add(srcArray, "5", 1);
		//srcArray[9] = "5";
//		System.out.println("最终插入值结果："+Arrays.toString(srcArray));
		
		List<String> list = new ArrayList<String>();
		list.add(2, "5");
		System.out.println(Arrays.toString(list.toArray()));
		
	}
	
	private boolean add(Object[] objArray, Object value, int index){
		if(index >= objArray.length || index < 0){
			throw new ArrayIndexOutOfBoundsException("数组下标越界");
		}
		int length = objArray.length - index ;
		if(length > 0){
			System.arraycopy(objArray, index , objArray, index + 1, length);
		}
		
		objArray[index] = value;
		return true;
	}
}
