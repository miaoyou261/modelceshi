package com.base.other;
/**
 * 常用测试输出
 * @author Administrator
 *
 */
public class MSystem {
public static void main(String[] args) {
	//转换符的测试
	System.out.printf("%,.2f \n" , 10000.00/3.0);//3,333.33  显示2位小数
	System.out.printf("%,(.2f \n" , 10000.00/3.0-500000);//(496,666.67) 讲负数用括号括起来
	System.out.printf("%,.3f \n" , 10000.00/3.0);//3,333.333 显示3位小数
	System.out.printf("%.2f" , 10000.00/3.0);//3333.33  没有使用逗号分隔
}
}
