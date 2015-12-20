package com.base.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class WriteText {
	public static void main(String[] args) {
//		write("测试文字");
		List<String> strList = readTxtFile("c:\\temp.txt");
	}
	/**
	 * 写入数据 如果有文件就覆盖重写
	 * @param date
	 */
	public static void write(String date,String src) {
		File f = new File(src);
		OutputStream out = null;
		try
		{
			out = new FileOutputStream(f);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		// 将字符串转成字节数组
		byte b[] = date.getBytes();
		try
		{
			// 将byte数组写入到文件之中
			out.write(b);
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		try
		{
			out.close();
		}
		catch (IOException e2)
		{
			e2.printStackTrace();
		}
	}
	/**
	 * 向文本文件里面追写数据
	 * @param Date 追加的数据
	 */
	public static void appendWrite(String Date,String src) {
		try {
		File filename = new File(src);
		if (!filename.exists())
		filename.createNewFile();
		FileWriter fileWriter;		
			fileWriter = new FileWriter(src, true);// true代表追加
		
		fileWriter.write(Date);
		fileWriter.flush();
		fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	 * 读取数据
	 * @param filePath
	 */
	public static List<String> readTxtFile(String filePath){
        try {
                String encoding="GBK";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    List<String> strList = new ArrayList<String>();
                    while((lineTxt = bufferedReader.readLine()) != null){
//                        System.out.println(lineTxt);
                        strList.add(lineTxt);
                    }
                    read.close();
                    return strList;
        }else{
            System.out.println("找不到指定的文件");
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
		return null;
     
    }
}
