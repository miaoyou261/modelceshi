package com.base.other;


import java.util.ArrayList;
import java.util.List;

public class ListCheshi {
public static void main(String[] args) {
//	ceshiContains();
//	List<String> oldUrl = new ArrayList<String>();
//	ceshiTransPort(oldUrl);
//	ceshiAdd();
	catString();
}



public static void catString() {
	List<String> mlist = new ArrayList<String>();
	mlist.add("http://www.a-hospital.com/w/ȫ��ҽԺ�б�");
	mlist.add("http://www.a-hospital.com/w/��ICU��Ϊ�ص���ҵ�ҽԺ�б�");
	mlist.add("http://www.a-hospital.com/w/���������Ϊ�ص���ҵ�ҽԺ�б�");
	mlist.add("http://www.a-hospital.com/w/�Թǿ�Ϊ�ص���ҵ�ҽԺ�б�");
	for (String string : mlist) {
		String src = "http://www.a-hospital.com/w/";
		if (string.contains("http://www.a-hospital.com/w/")) {
			string = string.substring(src.length(),string.length() );
			System.out.println(string);
		}
	}
	
}
/**
 * ����add �������Ƿ�����ǰ�������
 * ���Խ�� ����add���������ĩβ
 */
public static void ceshiAdd() {

	List<String> mlist = new ArrayList<String>();
	mlist.add("http://www.a-hospital.com/w/ȫ��ҽԺ�б�");
	mlist.add("http://www.a-hospital.com/w/��ICU��Ϊ�ص���ҵ�ҽԺ�б�");
	mlist.add("http://www.a-hospital.com/w/���������Ϊ�ص���ҵ�ҽԺ�б�");
	mlist.add("http://www.a-hospital.com/w/�Թǿ�Ϊ�ص���ҵ�ҽԺ�б�");
	for (String string : mlist) {
		System.out.println(string);
	}
	
}
/**
 * ���Դ��������ݲ����Ƿ����д������
 * List<String> oldUrl = null;
 * ���Խ�� -����
 * List<String> oldUrl = new ArrayList<String>();
 * ��������
 * @param oldUrl
 */
public static void ceshiTransPort(List<String> oldUrl) {
	oldUrl.add("�Ⱑ��");
	for (String string : oldUrl) {
		System.out.println(string);
	}
}
/**
 * ���Խ�� - List�а�����ĳ��������֮��ͬ������contains�ж�
 */
public static void ceshiContains() {
	List<String> mlist = new ArrayList<String>();
	mlist.add("http://www.a-hospital.com/w/ȫ��ҽԺ�б�");
	mlist.add("http://www.a-hospital.com/w/��ICU��Ϊ�ص���ҵ�ҽԺ�б�");
	mlist.add("http://www.a-hospital.com/w/���������Ϊ�ص���ҵ�ҽԺ�б�");
	mlist.add("http://www.a-hospital.com/w/�Թǿ�Ϊ�ص���ҵ�ҽԺ�б�");
	String o = "http://www.a-hospital.com/w/�Թǿ�Ϊ�ص���ҵ�ҽԺ�б�";
	if (mlist.contains(o)) {
		System.out.println(o);
	}
}
}
