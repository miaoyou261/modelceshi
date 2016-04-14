package com.base.splitHtml;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.base.util.DateUrllink;

public class jsonupSplit {
public static void main(String[] args) {
	showceshi();
}
public static void showceshi() {
	Document doc = getdoc("重庆市巴南区医院列表");//获取页面
	System.out.println(doc);
	Elements pngs = doc.select("title");//标签
	Elements pngs_id = doc.select("#firstHeading");//ID
	Elements pngs_class = doc.select(".mw-headline");//类
	Elements pngs_name = doc.select("[name]");//标签
//	Elements pngs_name = doc.select("[name]");//获取完全
	
	System.out.println(pngs);//获取
}

/**
 * 通过"XX医院列表"获取doc页面
 * 
 * @param doc
 * @param url
 * @return
 */
public static Document getdoc( String url) {
	Document doc = null;
	if (url.contains("（尚未撰写）")) {
		return null;
	}
	File input = new File("D:\\java\\" + url + ".txt");
	if (!input.exists()) {
		return null;
	}
	try {
		doc = Jsoup.parse(input, "UTF-8", "http://www.a-hospital.com/w/");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return doc;
}


}
