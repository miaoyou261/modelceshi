package com.base.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 关于传值和引用的问题
 * @author Administrator
 *
 */
public class PassValueAndquote {
	public static void main(String[] args) {
		showclear();
	}
	
	/**
	 * 处理引用数据
	 */
public static void quote() {
	for (int i = 0; i < 10; i++) {
		  NameAge nameAge1 =new NameAge("name"+i,i);
		  changequote(nameAge1);
	}
}
/**
 * 清理List数据对原数据是否有影响
 * 结果- List<NameAge> nameAgelist1 = nameAgelist; 的话就等于处理了2个list
 * 结果2 - List<NameAge> nameAgelist2 = new ArrayList<NameAge>(nameAgelist); 
 */
public static void showclear() {
	List<NameAge> nameAgelist = new ArrayList<NameAge>();
	for (int i = 0; i < 10; i++) {
		  NameAge nameAge1 =new NameAge("name"+i,i);
//		  changequote(nameAge1);
		  nameAgelist.add(nameAge1);
	}
	List<NameAge> nameAgelist1 = nameAgelist;
	List<NameAge> nameAgelist2 = new ArrayList<NameAge>(nameAgelist);
//				nameAgelist2 = nameAgelist;
	nameAgelist2.clear();
	System.out.println(nameAgelist.size());
	System.out.println(nameAgelist1.size());
	System.out.println(nameAgelist2.size());
	
}
/**
 * 改变引用数据对原始数据造成改变
 * @param nameage
 */
public static void changequote(NameAge nameage) {
	NameAge nameAge1 = nameage;
//	for (NameAge nameagestr : nameage) {
		System.out.println("1.原来的数据--"+nameage.age+nameage.name);
		System.out.println("1.原来的数据--"+nameAge1.age+nameAge1.name);
		nameAge1.age = 2;
		nameAge1.name = "变更"+2;
		System.out.println("2.原来的数据--"+nameage.age+nameage.name);
		System.out.println("2.原来的数据--"+nameAge1.age+nameAge1.name);
		//结果
//		1.原来的数据--1name1
//		1.原来的数据--1name1
//		2.原来的数据--2变更2
//		2.原来的数据--2变更2
//	}
	
}
}
