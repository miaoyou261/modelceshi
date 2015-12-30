package com.base.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 1.测试map内可以存放多少个对值 一个 还是N个
 * 2.测试引用对象处理后对象是否变更
 * @author Administrator
 *
 */
public class CeshiMap {

	final private static Map<String,NameAge> programMap = new HashMap<String,NameAge>();
	static List<NameAge> strlist = new ArrayList<NameAge>();
	public static void main(String[] args) {
//		howMuch();
		quote();
	}
	/**
	 * list通过Map引用删除后是否会出现位置
	 */
	public void removeCeshi() {
		
	}
	
	/**
	 * 测试引用对象处理后对象是否变更
	 * 结论 - 除基本类型外的引用是可以被变更的
	 */
	public static void quote() {
		
		for (int i = 0; i < 10; i++) {
			  NameAge nameAge1 =new NameAge("name"+i,i);
			strlist.add(nameAge1);			
		}
		for (int i = 0; i < strlist.size(); i++) {
			programMap.put(i+"k", strlist.get(i));
		}
		for (int i = 0; i < strlist.size(); i++) {
			NameAge nameage = programMap.get(i+"k");
			nameage.age=i*100;
		}
		for (int i = 0; i < strlist.size(); i++) {
			//System.out.println("programMap.get(i+k)="+programMap.get(i+"k"));
			System.out.println("strlist.get("+i+")"+strlist.get(i).age);
		}
	}
	
	/*
	 * 测试map内可以存放多少个对值 一个 还是N个
	 * 结果- 可以容纳N条数据
	 */
//	public static void howMuch() {
//		
//		for (int i = 0; i < 10; i++) {
//			programMap.put(i+"k", "v"+i);		
//		}
//		System.out.println(programMap.size());
//		for (int i = 0; i < 10; i++) {
//			System.out.println(programMap.get(i+"k"));	
//		}
//	}
	

}
