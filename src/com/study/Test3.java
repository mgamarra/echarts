package com.study;

/**
 * 编写一个截取字符串的函数，输入为一个字符串和字节数，输出为按字节截取的字符串。 
 * 但是要保证汉字不被截半个，如"我ABC"4，应该截为"我AB"，
 * 输入"我ABC汉DEF"，6，应该输出为"我ABC"而不是"我ABC+汉的半个"。
 * @author Yuanxihua
 *
 */
public class Test3 {

	public static void main(String[] args) {
		System.out.println(""+subString("我ABC汗DEF", 6));
	}

	public static boolean isLetter(char c){
		int k = 0x80;
		return c/k==0?true:false;
	}
	
	public static int lengths(String strSrc){
		if(strSrc == null){
			return 0;
		}
		int len = 0;
		char[] strChar = strSrc.toCharArray();
		for (int i = 0; i < strChar.length; i++) {
			len++;
			if(!isLetter(strChar[i])) len++;
		}
		return len;
	}
	
	public static String subString(String origin, int len){
		if(origin == null || origin.equals("") || len < 1){
			return "";
		}
		if(len>lengths(origin)){
			return origin;
		}
		byte[] strByte = new byte[len];
		System.arraycopy(origin.getBytes(), 0, strByte, 0, len);
		int count = 0;
		for (int i = 0; i < len; i++) {
			int value = (int)strByte[i];
			if(value < 0) count++;
		}
		if(count % 2 != 0){
			--len;
		}
		return new String(strByte, 0 ,len);
	}
}
