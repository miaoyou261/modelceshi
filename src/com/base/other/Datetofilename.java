package com.base.other;


import java.text.SimpleDateFormat;
/**
 * 时间日期转换测试
 * @author Administrator
 *
 */
public class Datetofilename {
	
	public static String  getFileName() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");  
	    java.util.Date date=new java.util.Date();  
	    String str=sdf.format(date);
	    System.out.println("显示正常日期: "+str);
		return str;
	}
public static void main(String[] args) {
	for (int i = 0; i < 10; i++) {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//延迟1000等于1秒
		getFileName();		
	}
}
}
