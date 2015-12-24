package com.base.other;

public class CeshiString {
public static void main(String[] args) {
	ceshiLang();
}
public static void ceshiLang() {
	String str = "测试长度";
	System.out.println(str.length());
	String strs[] = {"测试数组的长度","测试数组位标","对比两者之间的关系"};
	System.out.println(strs.length);
	System.out.println(strs[0]);
	System.out.println(strs[1]);
	System.out.println(strs[2]);
	//结论-数组是从零开始 而length是数组真实个数 两者之间的方式不同 无比较性，但是时不时的还是会出现弄错的情况
}
}
