package com.base.other;


import java.io.File;
import java.io.IOException;
/**
 * ·����ȡ����
 * @author Administrator
 *
 */
public class CeshiPath {
public static void main(String[] args) {
	System.out.println(System.getProperty("user.dir"));
	File directory = new File("");//�趨Ϊ��ǰ�ļ���

	    try {
			System.out.println(directory.getCanonicalPath());//��ȡ��׼��·��
		    System.out.println(directory.getAbsolutePath());//��ȡ����·��
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}
}
