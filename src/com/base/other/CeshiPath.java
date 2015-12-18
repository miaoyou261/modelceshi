package com.base.other;


import java.io.File;
import java.io.IOException;
/**
 * 路径获取测试
 * @author Administrator
 *
 */
public class CeshiPath {
public static void main(String[] args) {
	System.out.println(System.getProperty("user.dir"));
	File directory = new File("");//设定为当前文件夹

	    try {
			System.out.println(directory.getCanonicalPath());//获取标准的路径
		    System.out.println(directory.getAbsolutePath());//获取绝对路径
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}
}
