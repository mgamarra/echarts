package com.study;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 实现统计文本文件中某个单词的出现频率，并输出统计结果
 * @author Yuanxihua
 *
 */
public class Test4 {

	public static void main(String[] args) throws IOException{
		countNum();
	}
	
	public static void countNum() throws IOException{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("f:/file.txt"));
			Map<String, Object> map = new HashMap<String, Object>();
			for (String s = br.readLine(); s!= null; s=br.readLine()) {
				StringTokenizer st = new StringTokenizer(s, ",. ;");
				while(st.hasMoreElements()){
					String temp = st.nextToken();
					if(map.containsKey(temp)){
						map.put(temp, new Integer((Integer)map.get(temp)+1));
					}else{
						map.put(temp, new Integer(1));
					}
				}
			}
			for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
				Map.Entry entry = (Map.Entry)it.next();
				System.out.println(entry.getKey()+"-->"+entry.getValue()+"times");
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			br.close();
		}
	}
}
