package com.base.other;


import java.util.ArrayList;
import java.util.List;
/**
 * 
 * 
 * @author Administrator
 *
 */
public class ListCheshi {
	
public static void main(String[] args) {
//	ceshiContains();
//	List<String> oldUrl = new ArrayList<String>();
//	ceshiTransPort(oldUrl);
//	ceshiAdd();
	catString();
}



public static void catString() {
	List<String> mlist = new ArrayList<String>();
	mlist.add("http://www.a-hospital.com/w/全国医院列表");
	mlist.add("http://www.a-hospital.com/w/以ICU科为重点科室的医院列表");
	mlist.add("http://www.a-hospital.com/w/以泌尿外科为重点科室的医院列表");
	mlist.add("http://www.a-hospital.com/w/以骨科为重点科室的医院列表");
	for (String string : mlist) {
		String src = "http://www.a-hospital.com/w/";
		if (string.contains("http://www.a-hospital.com/w/")) {
			string = string.substring(src.length(),string.length() );
			System.out.println(string);
		}
	}
	
}
/**
 * 测试add 后数据是放入最前还是最后
 * 测试结果 数据add都是添加在末尾
 */
public static void ceshiAdd() {

	List<String> mlist = new ArrayList<String>();
	mlist.add("http://www.a-hospital.com/w/全国医院列表");
	mlist.add("http://www.a-hospital.com/w/以ICU科为重点科室的医院列表");
	mlist.add("http://www.a-hospital.com/w/以泌尿外科为重点科室的医院列表");
	mlist.add("http://www.a-hospital.com/w/以骨科为重点科室的医院列表");
	for (String string : mlist) {
		System.out.println(string);
	}
	
}
/**
 * 测试传递无数据参数是否可以写入数据
 * List<String> oldUrl = null;
 * 测试结果 -不能
 * List<String> oldUrl = new ArrayList<String>();
 * 测试正常
 * @param oldUrl
 */
public static void ceshiTransPort(List<String> oldUrl) {
	oldUrl.add("测啊是");
	for (String string : oldUrl) {
		System.out.println(string);
	}
}
/**
 * 测试结果 - List中包含的某条数据与之相同可以用contains判断
 */
public static void ceshiContains() {
	List<String> mlist = new ArrayList<String>();
	mlist.add("http://www.a-hospital.com/w/全国医院列表");
	mlist.add("http://www.a-hospital.com/w/以ICU科为重点科室的医院列表");
	mlist.add("http://www.a-hospital.com/w/以泌尿外科为重点科室的医院列表");
	mlist.add("http://www.a-hospital.com/w/以骨科为重点科室的医院列表");
	String o = "http://www.a-hospital.com/w/以骨科为重点科室的医院列表";
	if (mlist.contains(o)) {
		System.out.println(o);
	}
}
}
