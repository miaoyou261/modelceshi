package com.base.base.controlStructure;

import java.util.Enumeration;

public class _While {
public static void main(String[] args) {
	showDemo();
}
public static void showDemo() {
	int a = 1;
	//对就执行里面的
	while (a<5) {
		a++;
		System.out.println(a);
	}
	//缺衣少食- 1-10
	do {
		a++;
		System.out.println(a);
	} while (a<9);
}
}
