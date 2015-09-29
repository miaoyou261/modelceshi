package com.base.base.controlStructure;

import java.util.Arrays;

public class _Array {
public static void main(String[] args) {
//	show();
//	suijishow();
	HighDimension();
}
/**
 * 高维度用法
 */
public static void HighDimension() {
	int[][][][][][][][][] w = new int[2][2][3][4][5][6][7][8][9];
	int s = 0;
	for (int i = 0; i < w.length; i++) {
		int[][][][][][][][] w1 = w[i];
		for (int j = 0; j < w1.length; j++) {
			int[][][][][][][] w2 = w1[i];
			for (int k = 0; k < w2.length; k++) {
				int[][][][][][] w3 = w2[i];
				for (int m = 0; m < w3.length; m++) {
					int[][][][][] w4 = w3[i];
					for (int l = 0; l < w4.length; l++) {
						int[][][][] w5 = w4[i];
						for (int n = 0; n < w5.length; n++) {
							int[][][] w6 = w5[i];
							for (int o = 0; o < w6.length; o++) {
								int[][] w7 = w6[i];
								for (int p = 0; p < w7.length; p++) {
									int[] w8 = w7[i];
									for (int q = 0; q < w8.length; q++) {
										int w9 = w8[i];
										s++;
										w[i][j][k][m][l][n][o][p][q] = s;
										System.out.println("w["+i+"]["+j+"]["+k+"]["+m+"]["+l+"]["+n+"]["+o+"]["+p+"]["+q+"]"+w[i][j][k][m][l][n][o][p][q]);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
}


/**
 * 基础用法
 */
public static void show() {
	//创建数组的方式
	int[] i = {1,2,3,4,5,6,7,8,9,10};
	//方式二
	int[] b = new int[] {1,2,3,4,5,6,7,8,9,10};
	//引用变更
	System.out.println(b[2]);
	b[2] = 99;
	System.out.println(b[2]);
	//拷贝数据增加长度
	System.out.println(i.length);
	i = Arrays.copyOf(i, i.length+2);
	System.out.println(i.length);
	
	
	
	}
/**
 * 获得随机数
 */
public static void suijishow() {
	int[] i = new int[50];
	for (int j = 0; j < i.length; j++) {
		i[j] = j;
	}
	int[] r = new int[7];
	int o = (int)(Math.random()*49);// 获得随机数
	System.out.println(o);
}
}
