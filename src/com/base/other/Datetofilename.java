package com.base.other;


import java.text.SimpleDateFormat;
/**
 * ʱ������ת������
 * @author Administrator
 *
 */
public class Datetofilename {
	
	public static String  getFileName() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");  
	    java.util.Date date=new java.util.Date();  
	    String str=sdf.format(date);
	    System.out.println("��ʾ��������: "+str);
		return str;
	}
public static void main(String[] args) {
	for (int i = 0; i < 10; i++) {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//�ӳ�1000����1��
		getFileName();		
	}
}
}
