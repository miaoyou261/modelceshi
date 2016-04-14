package com.base.other;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 时间日期转换测试
 * @author Administrator
 *
 */
public class Datetofilename {
	/**
	 * 年月日时分秒毫秒
	 * @return
	 */
	public static String  getFileName() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");  
	    java.util.Date date=new java.util.Date();  
	    String str=sdf.format(date);
	    System.out.println("显示正常日期: "+str);
		return str;
	}
public static void main(String[] args) {
//	changeDate(new Date());
//	calculateDate();
	getWeek();
//	for (int i = 0; i < 10; i++) {
//		try {
//			Thread.sleep(10);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}//延迟1000等于1秒
//		getFileName();		
//	}
}
/**
 * 改变当前时间
 * @param da
 */
public static void changeDate(Date da) {
	Calendar calendar=Calendar.getInstance();  
	   calendar.setTime(new Date());
	   calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)-1);//让日期加1 
	   calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)-3);//让日期加1 
	   calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)-20);//让日期加1 

	   System.out.println(calendar.get(Calendar.YEAR)+"年");//加1之后的日期Top 
	   System.out.println(calendar.get(Calendar.MONTH)+"月");//加1之后的日期Top 
	   System.out.println(calendar.get(Calendar.DAY_OF_MONTH)+"日");//加1之后的日期Top 
	   calculateDate(calendar.getTime());
}
/**
 * 计算时间
 * 某时间距离当前时间有多久
 */
public static void calculateDate(Date da) {
	Calendar ca=Calendar.getInstance();  
	ca.setTime(new Date());
	   Calendar incalendar=Calendar.getInstance();  
	   incalendar.setTime(da);
		int year = ca.get(Calendar.YEAR) - incalendar.get(Calendar.YEAR);
		int Month = ca.get(Calendar.MONTH) - incalendar.get(Calendar.MONTH);
		int day = ca.get(Calendar.DAY_OF_MONTH) - incalendar.get(Calendar.DAY_OF_MONTH);
	   System.out.println(year+"年");//加1之后的日期Top 
	   System.out.println(Month+"月");//加1之后的日期Top 
	   System.out.println(day+"日");//加1之后的日期Top 
//	   System.out.println(calendar.get(calendar.DAY_OF_MONTH));//加1之后的日期Top 
	
}
/**
 * 加减时间
 */
public static void calculateDate() {
	System.out.println(new Date().getTime());
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	String dateString = "2016-01-01 11:11:11";

	Calendar calendar = Calendar.getInstance();

	long nowDate = calendar.getTime().getTime(); //Date.getTime() 获得毫秒型日期

	

	long specialDate = 0;
	try {
		specialDate = sdf.parse(dateString).getTime();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Double dou = 21d;
	long betweenDate = (specialDate - 21*(1000 * 60 * 60 * 24))  ; //计算间隔多少天，则除以毫秒到天的转换公式
	Date date = new Date(betweenDate);
	System.out.println(dou.longValue());
	System.out.print(date);
//	sdf.format(date);
	System.out.print(sdf.format(date));
}


public static void getWeek() {
	Calendar calendar = Calendar.getInstance();

//	calendar.setTime(new java.util.Date());

	int week = calendar.get(Calendar.DAY_OF_WEEK)-1;
	System.out.println("星期"+week);
}
}
