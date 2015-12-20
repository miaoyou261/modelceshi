package com.base.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * 获取医院地址 -完成度90% 2015-12-18
 * @author Administrator
 *
 */
public class LinksDate {
	private final static String urlHead = "http://www.a-hospital.com/w/";// 页面前缀
	private final static String srcHead = "D:\\java\\web\\";// 文件位置
	

	public static void main(String[] args) {

		DateUrllink urlDate = DateUrllink.getInstance();
		List<String> oldUrl = new ArrayList<String>();
		List<String> newUrl = new ArrayList<String>();
		List<String> linksUrl = new ArrayList<String>();
		urlDate.setOldUrl(oldUrl);
		urlDate.setLinksUrl(linksUrl);
		urlDate.setNewUrl(newUrl);
		init("全国医院列表");		
		 System.exit(0);
	}
	
	public static void init(String url) {
		Document doc = null;
		doc = getdoc(doc, url);// 获取doc页面数据
		List<String> urlList = new ArrayList<String>();
		urlList = MainCity(doc);// 获取主要城市的页面
		for (String maincityurl : urlList) {
			Document doc1 = null;
			System.out.println("查看显示是否正常--"+maincityurl);
			doc1 = getdoc(doc1, maincityurl);// 获取doc页面数据
			checkBottom(doc1);// 检测是否到底部
		}
	}

	/**
	 * 检测是否在底部
	 * 
	 * @param doc
	 */
	public static void checkBottom(Document doc) {
		DateUrllink urlDate = DateUrllink.getInstance();
		Document doc1 = null;
		List<String> urlList = new ArrayList<String>();
		urlList = child(doc);// 获取下级城市连接
		if (urlList.size() > 0) {// 还有子级别
			for (String string : urlList) {
				if (!urlDate.getLinksUrl().contains(string)) {//检测是否有重复的页面
					doc1 = getdoc(doc1, string);					
				}
				if (doc1!=null) {
					checkBottom(doc1);//回调自身					
				}
			}
		} else {//如果没有子级别了就是最后一层
			splitStr(doc);
		}

//		GetLevel(doc);// 显示页面所在级别----注：暂时没发现如何利用这个级别
	}

	/**
	 * 通过"XX医院列表"获取doc页面
	 * 
	 * @param doc
	 * @param url
	 * @return
	 */
	public static Document getdoc(Document doc, String url) {
		DateUrllink urlDate = DateUrllink.getInstance();
		if (urlDate.getLinksUrl().contains(url)) {
			return null;
		}
		if (url.contains("（尚未撰写）")) {
			return null;
		}
//		if (url.contains("厦门市思明区医院列表")) {
//			System.out.println();
//		}
		urlDate.getLinksUrl().add(url);
		doc = GetDate(srcHead + url + ".txt");// 查看本地是否有
		if (doc == null) {
			doc = geturl(urlHead + url);
		}
		return doc;
	}
	/**
	 * 拆分每组数据到一行
	 * @param doc
	 */
	public static void splitStr(Document doc) {
		Elements links = doc.select("div#bodyContent>ul:contains(经营方式)").select("li");
		for (Element element : links) {
//			GetLevel(doc);// 显示页面所在级别----注：暂时没发现如何利用这个级别
			String a = GetLevel(doc)+","+element.select("a[title]").text();
			System.out.print(a);
//			WriteText.appendWrite("\n"+a,"c:\\temp.txt");//追写入文件中
			Elements li = element.select("ul>li");
			if (li.size()>0) {
			for (Element element2 : li) {
//				String btext = element2.select("b").text();
				String litext = element2.select("li").text();
//				System.out.println(btext);	
				a += ","+litext;
				System.out.print(","+litext);			
			}
			if (a.length()>0) {
				WriteText.appendWrite("\n"+a,"c:\\hospitalDate.txt");//追写入文件中
				System.out.println();					
			}
		}
				
		}
		
	}
	

	/**
	 * 获取页面数据
	 * 
	 * @param url
	 * @return
	 */
	public static Document geturl(String url) {// 这里需要重新改成连接N次就不据需连接了
		Document doc = null;		
		try {
			while (doc == null) {
				Thread.sleep(5000);
				doc = Jsoup.connect(url).get();
				saceUrltoFile(url, doc);// 将获取的页面存入本地
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			WriteText.appendWrite("\n" + url, "c:\\errorsave.txt");
			e.printStackTrace();
		}
		return doc;
	}

	/**
	 * 将取得的页面保存到本地
	 * 
	 * @param url
	 * @param doc
	 */
	public static void saceUrltoFile(String url, Document doc) {
		try {
			String src = urlHead;
			if (url.contains(src)) {
				String name = url.substring(src.length(), url.length());
				File mfile = new File(srcHead + name
						+ ".txt");
				if (!mfile.exists()) {
					WriteText.write("\n" + doc.toString(),
							srcHead + name + ".txt");
					WriteText.appendWrite("\n" + time()
							+ srcHead + name + ".txt",
							srcHead+"log.txt");
					// System.out.println(""+url);
				} else {
					WriteText.write("\n" + doc.toString(),
							srcHead+"repeat\\" + name
									+ ".txt");
					WriteText.appendWrite("\n" + time()
							+ srcHead+"repeat\\" + name
							+ ".txt", srcHead+"log.txt");
					// System.out.println("重复的文件:"+url);
				}
			} else {
				System.out.println(time() + "有未写入的文件:" + url);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("保存文件失败" + url);
			e.printStackTrace();

		}
	}

	/**
	 * 获取主要城市页面
	 * 
	 * @param doc
	 */
	public static List<String> MainCity(Document doc) {
		Elements pngs = doc.select("area");
		List<String> strlist = new ArrayList<String>();
		for (Element element : pngs) {
			String str = element.attr("abs:href");// 获取href中的相对连接改成绝对连接
			str = transformCode(str);
			str = str.substring(urlHead.length(), str.length());
			System.out.println("获取主要城市页面连接:" + str);
			strlist.add(str);
		}
		return strlist;
	}
	
	/**
	 * 截取医院等级以上的数据
	 * @param doc
	 */
	public static Document split(Document doc) {
		String newDate = "";
		String[] gdoc = doc.toString().split("\n");
		for (String str : gdoc) {
			if (str.contains("title=\"医院等级\"")||str.contains("<h2>")) {
//				System.out.println("截取医院以上的数据"+str);
				break;
			}
			newDate+=str+"\n";
		}	
		System.out.println("截取医院以上的数据"+newDate);
		 return doc = Jsoup.parse(newDate);			
	}

	/**
	 * 获取省市下级区域
	 * 
	 * @param doc
	 * @return
	 */
	public static List<String> child(Document doc) {
		doc = split(doc);
		//方式一
		Elements pngs = doc.select("div#bodyContent>p>a[title],div#bodyContent>ul>li>a[title]");
		List<String> strlist = new ArrayList<String>();
		for (Element element : pngs) {
//			String str = element.attr("href");
			String str = element.attr("title");// 获取href中的相对连接改成绝对连接
//			str = transformCode(str);
//			str = str.substring(urlHead.length(), str.length());
			if (str.equals("医院等级")) {
//				System.out.println(element);
				break;
			}
			strlist.add(str);
		}
		//方式二
//		Elements pngs2 = doc.select("div#bodyContent>ul>li>a[title]");
//		for (Element element : pngs2) {
//			String str = element.attr("title");// 获取href中的相对连接改成绝对连接
////			str = transformCode(str);
////			str = str.substring(urlHead.length(), str.length());
//			if (str.equals("医院等级")) {
////				System.out.println(element);
//				break;
//			}
//			strlist.add(str);
//		}
		return strlist;
	}

	/**
	 * 获取级别数据
	 * 
	 * @param doc
	 */
	public static String GetLevel(Document doc) {

		Elements pngs = doc.select("table.nav");
		String tile = "";
		for (Element element : pngs) {
			String str = element.text();
			String[] strs = str.split(">>");
			for (String string : strs) {
				if (!string.equals("A+医学百科 ")) {
//					if (string.contains("长春市医院列表")) {
//						System.out.println(string);						
//					}
					System.out.println(string);
					tile+= string;					
				}
			}
		}
		return tile;
	}

	/**
	 * 从文件获取数据
	 * 
	 * @param src
	 * @return
	 */
	public static Document GetDate(String src) {
		Document doc = null;
		File input = new File(src);
		if (!input.exists()) {
			return null;
		}
		try {
			doc = Jsoup.parse(input, "UTF-8", urlHead);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}

	/**
	 * 显示日志时间
	 * 
	 * @return
	 */
	public static String time() {
		Date nowTime = new Date(System.currentTimeMillis());
		SimpleDateFormat sdFormatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss(SSS 毫秒)");
		String retStrFormatNowDate = sdFormatter.format(nowTime);

		return retStrFormatNowDate;
	}

	/**
	 * 将连接码转码成中文
	 * 
	 * @param url
	 * @return
	 */
	public static String transformCode(String url) {
		// String str = "/w/西安市医院列表";
		try {
			url = URLDecoder.decode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
}
