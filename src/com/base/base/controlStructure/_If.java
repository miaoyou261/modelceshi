package com.base.base.controlStructure;

public class _If {
public static void main(String[] args) {
//	baseMethod();
//	ternaryOperato();
	complex();
}
/**
 * if 语句用法
 */
public static void baseMethod() {
	int a =1;
	int b =2;
	int c =3;
	int d =4;
	//通用 用法
	if (a==b) {
		System.out.println("通用 用法"+a+"果然等于"+b);
	}
	//简写方式
	if (a==b)
		System.out.println("简写方式"+a+"居然等于"+b);
	
	
	//如果。。。否则
	if (a!=b) {
		System.out.println("如果"+a+"居然不等于"+b);
	} else {
		System.out.println("否则"+a+"果然等于"+b);
	}
	
	//如果。。。或者。。。或者。。。否则
	if (a!=b) {
		System.out.println("如果"+a+"居然不等于"+b);
	} else if(a!=b){
		System.out.println("或者"+a+"居然不等于"+b);
	} else if(a!=b){
		System.out.println("或者"+a+"居然不等于"+b);
	} else {
		System.out.println("否则"+a+"和"+b+"没半毛钱关系");
	}
	
}
//复杂的简写
public static void complex() {
	int x = -1;
	int sign = 2;
	if(x<=0) if(x==0)sign = 0; 
	else sign = -1;
	System.out.println("x=="+x);
	System.out.println("sign=="+sign);
	
	//正确的理解
	x = -1;
	sign = 2;
	if(x<=0) {if(x==0)sign = 0; else sign = -1;}
	System.out.println("加括号1");
	System.out.println("x=="+x);
	System.out.println("sign=="+sign);
	
	//错误的理解
	x = -1;
	sign = 2;
	if(x<=0) {if(x==0)sign = 0;} else sign = -1;
	System.out.println("加括号2");
	System.out.println("x=="+x);
	System.out.println("sign=="+sign);
}
//三目运算
public static void ternaryOperato() {
	//前面的对了 后面的错了
 Boolean a = false?false:true;
 Boolean b = true?true:false;
 System.out.println(a);
 System.out.println(b);
}



}
