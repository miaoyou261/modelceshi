package com.base.base.controlStructure;

import java.util.Scanner;

public class _Switch {
public static void main(String[] args) {
	show();
}
/**
 * 只是没想到这玩意也可以 嵌套
 */
public static void show() {
	System.out.println("请输入字符");
	Scanner in = new Scanner(System.in);
	int choice = in.nextInt();
	switch (choice) {
	case 1:
		System.out.println(choice);
		break;
	case 2:
		System.out.println(choice);
		String ser = "牛逼";
		switch (ser) {//好像并不是只有数字才可以
		case "牛牛":
			System.out.println("牛牛");
			break;
		case "牛逼":
			System.out.println("牛逼");
			break;
		case "牛":
			System.out.println("牛");
			break;

		default:
			break;
		}
		break;
	case 3:
		System.out.println(choice);
		break;

	default:
		System.out.println("这里什么都没有");
		break;
	}
}
}
