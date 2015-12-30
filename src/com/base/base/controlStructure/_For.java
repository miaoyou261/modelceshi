package com.base.base.controlStructure;

public class _For {
public static void main(String[] args) {
	errorShow();
}

/**
 * for 结构展示
 */
public void show() {
	for (int i = 10; i > 0; i--) 
		System.out.println("显示结果"+i);
	
	System.out.println("blastoff");
	
}
/**
 * 会出现问题的使用方法
 * 无法结束的"正常"for循环
 */
public static void errorShow() {
	for (double i = 0; i !=10; i+=0.1) 
		System.out.println("显示结果"+i);
	
	System.out.println("blastoff");
}
}
