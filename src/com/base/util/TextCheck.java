package com.base.util;

import java.util.ArrayList;
import java.util.List;

public class TextCheck {
	public static void main(String[] args) {
//		contrastText();
		checkRegion();
		
	}
	
	
	/**
	 * 对比是否有重复的数据
	 */
public static void contrastText() {
	List<String> MYHospito =readDate("c:\\MYhospitalDate.txt");
	List<String> Myhospito =new ArrayList<String>();
	//过滤掉title部分数据
//	for (int i = 0; i < Myhospito.size(); i++) {
//		String[] str= Myhospito.get(i).split(",");
//		Myhospito.add(str[1]);
//	}
	for (String string : MYHospito) {
		if (string.length()>0) {
			String[] str= string.split(",");
			Myhospito.add(str[1]);
		}
	}
	MYHospito.clear();
	List<String> Hospito =readDate("c:\\hospitalDate.txt");
	List<String> hospito =new ArrayList<String>();
	//过滤掉title部分数据
		for (String string : Hospito) {

			if (string.length()>0) {
			String[] str= string.split(",");
			hospito.add(str[0]);
		}
			}
		Hospito.clear();
		
		//开始对比数据
		for (int i = 0; i < hospito.size(); i++) {
			int nb = 0;//记录Myhospito中出现过多少次
			for (int j = 0; j < Myhospito.size(); j++) {
				if (hospito.get(i).equals(Myhospito.get(j))) {
					if (nb==0) {
						nb++;
					}else if(nb>0){
						nb++;
						System.out.println("重复出现"+nb+"次的医院："+hospito.get(i));
					}
				}
				
			}
			if (nb==0) {//一次都没有出现的话
				System.out.println("Myhospito中没有的医院-"+i+"--"+hospito.get(i));
			}
		}
		
		//开始对比数据
				for (int i = 0; i < Myhospito.size(); i++) {
					int nb = 0;//记录Myhospito中出现过多少次
					for (int j = 0; j < hospito.size(); j++) {
						if (j==17496) {
							System.out.println(j);
						}
						if (hospito.get(j).equals(Myhospito.get(i))) {
							if (nb==0) {
								nb++;
							}else if(nb>0){
								nb++;
								System.out.println("重复出现"+nb+"次的医院："+Myhospito.get(i));
							}
						}
						
					}
					if (nb==0) {//一次都没有出现的话
						if (i==30575) {
							System.out.println("hospito中没有的医院-"+i+"--");
						}
						System.out.println("hospito中没有的医院-"+i+"--"+Myhospito.get(i));
					}
				}
	
}
/**
 * 从文本读取数据医院信息 检测是否有重复的行
 */
public static List<String> readDate(String src) {
	List<String> strlist = WriteText.readTxtFile(src);
	for (int i = 0; i < strlist.size(); i++) {
		for (int j = i+1; j < strlist.size(); j++) {
			if (strlist.get(i).equals(strlist.get(j))) {
				System.out.println("第"+i+"行和第"+j+"行重复"+strlist.get(i));
			}
		}
	}
	return strlist;
}
/**
 * 校验重复的行
 */
public static void checkRegion() {
	List<String> strlist = WriteText.readTxtFile("c:\\行政区域码表.txt");
	for (int i = 0; i < strlist.size(); i++) {
		for (int j = i+1; j < strlist.size(); j++) {
			if (strlist.get(i).equals(strlist.get(j))) {
				strlist.remove(j);
				j--;
				System.out.println("第"+i+"行和第"+j+"行重复"+strlist.get(i));
			}
		}
	}
	//写入文本
	for (String string : strlist) {
		 WriteText.appendWrite(string+"\n", "c:\\行政区域码表new.txt");
	}
	
}
}
