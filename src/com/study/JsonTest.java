package com.study;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import echarts.User;

public class JsonTest {

	public static void main(String[] args) {
		/*
		String text = "[{'username':'小华','password':'123456'},{'username':'小华','password':'123456'}]";
		List list = JSON.parseObject(text, List.class);
		for (Object object : list) {
			Map<String, String> map = (Map<String, String>)object;
			System.out.println(map.get("username")+"  "+map.get("password"));
		}
		*/
		/*
		String text = "{\"username\":\"小华\",\"password\":\"123456\"}";
		User user = JSON.parseObject(text, User.class);
		System.out.println(user.getUsername()+"  "+user.getPassword());
		*/
		
		/*
		List<User> list = new ArrayList<User>();
		list.add(new User("小明","123456"));
		list.add(new User("小亮","456789"));
		String objectJson = JSON.toJSONString(list);
		System.out.println(objectJson);
		*/
		String objectJson = JSON.toJSONString(new User("小明","123456"));
		System.out.println(objectJson);
		
		
	}

}
