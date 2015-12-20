package com.base.other;


import java.io.File;
import java.io.IOException;
/**
 * 图片存储测试
 * @author Administrator
 *
 */
public class CeshiFileSave {
public static void main(String[] args) {
	FileNotOrExist();
}
/**
 * 测试文件是否存在判断
 */
public static void FileNotOrExist() {
	File file = new File("c:\\tem.txt");
	if (!file.exists()) {
		System.out.println("文件不存在");
		 try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}else{

		System.out.println("文件存在");
	}
}
/**
 * 测试图片存储
 */
public void photoSave() {
	File file = new File("c:\\A\\B\\C");
	if (file!=null) {
	  file.mkdirs();			
	}
	  file = new File("c:\\A\\B\\D");
	  if (file!=null) {
		  file.mkdir();			
		}
	  System.out.println(System.getProperty("user.dir"));
	  File file1 = new File(System.getProperty("user.dir")+"/C.jpg");
	  if (!file1.exists()) {
		  try {
			  file1.createNewFile();
		  } catch (IOException e) {
		  e.printStackTrace();
		  }
}
}
}
