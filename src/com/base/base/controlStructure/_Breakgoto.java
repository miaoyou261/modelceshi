package com.base.base.controlStructure;

public class _Breakgoto {
public static void main(String[] args) {
	showfor();
}

/**
 * 好像还有一种continue标记的方式 书中没有说明
 */
public static void showfor() {
	int i = 100;
	out:{
	for (i = 0; i != 10; i+=3) {
		for (int j = 0; j != 10; j+=6) {
			System.out.print(i);
			if (j>10) {
				i-=9;
				break  out;//只是用来跳出最外层循环 病不是用来跳到标记处
			}
		}
		
	}
	}
	outooo:
		for (i = 0; i != 10; i+=3) {
			for (int j = 0; j != 10; j+=6) {
				System.out.print(i);
				if (j>10) {
					i-=9;
					continue  outooo;//这个可以跳出
				}
			}
			
		}
}
}
