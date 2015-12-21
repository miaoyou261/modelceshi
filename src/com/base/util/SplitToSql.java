package com.base.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.base.mysql.TestUser;

public class SplitToSql {
public static void main(String[] args) {
	readDate();
//	ceshi();
}
///**
// * 测试是否有重复的数据-- 结果：没有
// */
//public static void ceshi() {
//	List<String> strlist = WriteText.readTxtFile("c:\\hospitalDate.txt");
//	for (int i = 0; i < strlist.size(); i++) {
//		for (int j = i+1; j < strlist.size(); j++) {
//			if (strlist.get(i).equals(strlist.get(j))) {
//				System.out.println(strlist.get(j));
//			}
//		}		
//	}
//}

//public Connection mysqlJdbc() {
//	Connection conn = null;
//	try {
//	// 加载驱动程序
//	Class.forName("com.mysql.jdbc.Driver");
//	// 获取连接(这里用户名为root，密码为空)
//	conn = DriverManager.getConnection(
//	"jdbc:mysql://localhost:3306/ceshi", "root", "123456");
//	} catch (ClassNotFoundException e) {
//	e.printStackTrace();
//	} catch (SQLException e) {
//	e.printStackTrace();
//	}
//	return conn;
//}
/**
 * 分割每条数据 筛选title
 * @param string
 */
public static void	spiltTitle(String string) {
	if (string.length()>0) {
		String[] str = string.split(",");
		String[] str1 = str[0].split("医院列表");
		List<String[]> lidtr = new ArrayList<String[]>();
		for (String string2 : str1) {
			string2 =string2.trim();
			String string1 = readRegion(string2).trim();
			lidtr.add(new String[]{"region_code",string1});
			lidtr.add(new String[]{"retion_name",string2});
			TestUser.ceshiRegionSql(lidtr);
//			lidtr.add(new String[]{"region_code",string1});
//			lidtr.add(new String[]{"region_code",string1});
//			lidtr.add(new String[]{"region_code",string1});
//			lidtr.add(new String[]{"region_code",string1});
//			String[] parent_region_code = {"parent_region_code",string1};
//			String[] retion_name = {"retion_name",string2};
//			String[] has_sub_region = {"has_sub_region",string1};
//			String[] level = {"level",string1};
		}
		
	}
}

/**
 * 分割每条数据 3W条数据
 * @param string
 */
public static void	spiltRow(String string) {
	if (string.length()>0) {
		String[] str = string.split(",");
		for (String string2 : str) {//读取被分割的数据
			String[] str1 = string2.split("：");
			if (str1.length==1) {//获取医院名称
				System.out.println(str1);
			}
			
			String head = "";
			String other = "";
			for (int i = 0; i < str1.length; i++) {
				if (i==0) {
					head = str1[i];
				}  else {
					other += str[i];
				}				
			}
			
			if (str1.length>1) {
				if (head.equals("医院地址")) {	
					
				} else if (head.equals("联系电话")){
				} else if (head.equals("医院等级")){
				} else if (head.equals("经营方式")){
				} else if (head.equals("重点科室")){
				} else if (head.equals("电子邮箱")){
				} else if (head.equals("医院网站")){
				} else if (head.equals("传真号码")){
				} else if (head.equals("医院网站")){
				} else if (head.equals("医院网站")){
				} else if (head.equals("医院网站")){
				} else if (head.equals("医院网站")){
				}
			}
			System.out.println(string2);
		}
	}
}
/**
 * 从文本读取数据医院信息
 */
public static void readDate() {
	List<String> strlist = WriteText.readTxtFile("c:\\hospitalDate.txt");
	for (String string : strlist) {//读取行数据
//		spiltRow(string);
		spiltTitle(string);
	}
}
/**
 * 从文本读取行政区域数据
 * 返回区域代码
 */
public static String readRegion(String str) {
	List<String> strlist = WriteText.readTxtFile("c:\\行政区域码表.txt");
	for (String string : strlist) {//读取行数据
		if (string.contains(str)) {
//			try {
//				byte[] b2=string.getBytes("utf-8");
//				String ss=new String(b2,"utf-8");
			string=string.trim();
				String[] s =string.split("(?=[a-z]*[a-z])(?<=[0-9]*[0-9])|(?=[\\u4e00-\\u9fa5]*[\\u4e00-\\u9fa5])");
				return s[0].trim();
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
	return null;
}
}
