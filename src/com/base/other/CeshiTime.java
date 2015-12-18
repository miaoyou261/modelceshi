package com.base.other;


import java.text.SimpleDateFormat;
import java.util.Date;

public class CeshiTime {
	
	public static void main(String[] args) {
		System.out.println(refFormatNowDate());
	}
	/**
	 * 获取当前时间
	 * @return 2015-12-15 18:26:55(989 毫秒)
	 */
	public static String refFormatNowDate() {
		  Date nowTime = new Date(System.currentTimeMillis());
		  SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss(SSS 毫秒)");
		  String retStrFormatNowDate = sdFormatter.format(nowTime);

		  return retStrFormatNowDate;
		}
}
