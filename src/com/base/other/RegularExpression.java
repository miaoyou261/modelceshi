package com.base.other;
/**
 * 正则表达式测试
 * @author Administrator
 *
 */
public class RegularExpression {
public static void main(String[] args) {
	NB();
}
/**
 * 处理数字
 */
public static void NB() {
	String nb = "310000  　上海市";
	String[] nb0 = nb.split("(?<=^[0-9]^)");
	System.out.println(nb0[0]);//[310000  　上海市]
	String[] nb1 = nb.split("(?=[\\u4e00-\\u9fa5]*[\\u4e00-\\u9fa5])");//这里可以看出-和*号的不同
	System.out.println(nb1[0]);//[310000  　, 上, 海, 市]
	 nb1 = nb.split("(?=[\\u4e00-\\u9fa5]-[\\u4e00-\\u9fa5])");
		System.out.println(nb1[0]);//[310000  　上海市]
}
}
