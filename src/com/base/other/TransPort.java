package com.base.other;



/**
 * 结论将一个参数传入另一个方法后将其值改变并不影响对象在原方法中的值
 * @author Administrator
 *
 */
public class TransPort {
	
	
	public static void main(String[] args) {
		String a = "main";
		operating(a);
		System.out.println(a);
	}
	
	
	
	
	public static void operating(String a) {
		a = "operating";
	}

}
