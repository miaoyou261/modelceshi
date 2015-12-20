package com.base.other;



public class SrcChange {
/**
 * web路径转换成文件路径
 * 注意 : 要去除掉协议IP等
 * @param url
 */
public static String URLtoURI(String url) {
	String uri ="";
	String[] urls = url.split("/");
	for (String  s : urls) {
		if (s.length()>0) {
			uri+="\\"+s;			
		}
	}
	return uri;	
	
}
/**
 * 文件路径转换成web路径
 * @param uri
 * @return
 */

public static String URItoURl(String uri) {

	String url ="";
	String[] uris = uri.split("\\\\");//应为“\”本身就是转衣服所以一个“\”就等于是“\\”转义出来的 所以这里就是4个"\"
	for (String  s : uris) {
		if (s.length()>0) {
			url+="/"+s;			
		}
	}	
//	System.out.println("错误的路径:"+url);
//	System.out.println("正确路径:"+url.substring(0,url.length()-1));//去掉最后一个字符
//	System.out.println("文件路径:"+uri);
	return url;
}
/**
 * 将绝对路径转换成相对路径
 * @param urlStart 项目起始路径
 * @param Url	转换后的相对路径
 * @return	反馈相对路径
 */
public static String getSrc(String urlStart ,String Url) {
	if (urlStart!=null&&Url!=null) {//参数不为空
		if (urlStart.equals(Url.subSequence(0, urlStart.length()))) {//
			return Url.subSequence(urlStart.length(),Url.length()).toString();
		}
	}
	return Url;
}

public static void main(String[] args) {
	String uri = "E:\\workspace\\EclipseWorkSpace\\javademo\\public\\picture\\Credentials\\max9640533c-15cb-4e9f-8d89-8084af5db29420151127144552807.jpg";
	String URl="";
	URItoURl(uri);//这里仅仅是将路劲的正斜杠处理成反斜杠 ，web还不能访问
	System.out.println(URl);
	String s = System.getProperty("user.dir");
	System.out.println(System.getProperty("user.dir"));
	System.out.println(getSrc(s,uri));
	System.out.println(URItoURl(getSrc(s,uri)));

	//还要加上协议
	URl = "http://"+GetIP.GetServiceIP()+":9000"+URItoURl(getSrc(s,uri));
	System.out.println(URl);
	System.out.println(URLtoURI(URl));
	//获取本机局域网的IP地址-实际上可能还需要广域网的IP地址
}
}
