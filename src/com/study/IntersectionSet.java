package com.study;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Çó½»¼¯
 * @author Yuanxihua
 * 
 */
public class IntersectionSet {

	public static void main(String[] args) {
		IntersectionSet inter = new IntersectionSet();
		List<List<Integer>> lists = inter.getLists();
		Set<Integer> resultSet = new HashSet<Integer>();
		for (int i = 0; i < lists.size(); i++) {
			List<Integer> list = lists.get(i);
			if(i == 0){
				resultSet = inter.getResult(list);
			}else{
				resultSet = inter.getResult(resultSet, list);
				if(resultSet.size() == 0){
					break;
				}
			}
		}
		
		System.out.println(resultSet.toString());
	}
	
	private Set<Integer> getResult(List<Integer> list){
		Set<Integer> resultSet = new HashSet<Integer>();
		for (Integer integer : list) {
			resultSet.add(integer);
		}
		return resultSet;
	}

	private Set<Integer> getResult(Set<Integer> set, List<Integer> list){
		Set<Integer> resultSet = new HashSet<Integer>();
		for (Integer integer : list) {
			if(set.contains(integer)){
				resultSet.add(integer);
			}
		}
		return resultSet;
	}
	
	
	private List<List<Integer>> getLists(){
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(4);
		list1.add(4);
		list1.add(6);
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(1);
		list2.add(3);
		list2.add(4);
		list2.add(5);
		list2.add(3);
		List<Integer> list3 = new ArrayList<Integer>();
		list3.add(1);
		list3.add(4);
		list3.add(5);
		list3.add(6);
		list3.add(7);
		List<Integer> list4 = new ArrayList<Integer>();
		list4.add(4);
		list4.add(5);
		list4.add(6);
		list4.add(7);
		list4.add(8);
		list.add(list1);
		list.add(list2);
		list.add(list3);
		list.add(list4);
		return list;
	}
}


