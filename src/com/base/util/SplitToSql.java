package com.base.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

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
 * 分割每条数据
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
 * 从文本读取数据
 */
public static void readDate() {
	List<String> strlist = WriteText.readTxtFile("c:\\hospitalDate.txt");
	for (String string : strlist) {//读取行数据
		spiltRow(string);
	}
}
}
