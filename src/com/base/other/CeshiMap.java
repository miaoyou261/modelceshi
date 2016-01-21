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

	final private static Map<String, Object> programMapMap = new HashMap<String,Object>();
	final private static Map<String,NameAge> programMap = new HashMap<String,NameAge>();
	static List<NameAge> strlist = new ArrayList<NameAge>();
	public static void main(String[] args) {
//		howMuch();
//		quote();
//		compound();
//		removeCeshi();
		clearceshi();
	}
	/**
	 * clear对再次PUT无影响
	 * 测试清除数据后map的引用是否存在
	 */
	public static void clearceshi() {
		quote();
		programMap.clear();
		quote();
	}
	/**
	 * 测试使用remove后数据是被 【删除】还是被【删除引用】
	 */
	public static void removeCeshi() {
		System.out.println("有多少"+programMap.size()+"条  ");
		System.out.println("有多少"+strlist.size()+"条  ");
//		System.out.println("有多少"+programMap.size()+"条  "+programMap.get(programMap.size()-1).age+"   "+programMap.get(programMap.size()-1).name);
		programMap.remove("3k");
		System.out.println("有多少"+programMap.size()+"条  ");
		System.out.println("有多少"+strlist.size()+"条  ");
//		System.out.println("有多少"+programMap.size()+"条  "+programMap.get(programMap.size()-1).age+"   "+programMap.get(programMap.size()-1).name);
	}
	/**
	 * 向map 中放map的复合用法
	 */
	public static void compound() {
		programMapMap.put("测试", programMap);
		System.out.println(programMapMap.get("测试"));
		Map<String,NameAge> map = (Map<String,NameAge>)programMapMap.get("测试");
		for (String key : map.keySet()) {
			   System.out.println("key= "+ key + " and value= " + map.get(key).age);
			  }
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
	
	

}
