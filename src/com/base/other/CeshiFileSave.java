package com.base.other;


import java.io.File;
import java.io.IOException;
/**
 * ͼƬ�洢����
 * @author Administrator
 *
 */
public class CeshiFileSave {
public static void main(String[] args) {
	FileNotOrExist();
}
/**
 * �����ļ��Ƿ�����ж�
 */
public static void FileNotOrExist() {
	File file = new File("c:\\tem.txt");
	if (!file.exists()) {
		System.out.println("�ļ�������");
		 try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}else{

		System.out.println("�ļ�����");
	}
}
/**
 * ����ͼƬ�洢
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
