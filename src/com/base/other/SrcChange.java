package com.base.other;



public class SrcChange {
/**
 * web·��ת�����ļ�·��
 * ע�� : Ҫȥ����Э��IP��
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
 * �ļ�·��ת����web·��
 * @param uri
 * @return
 */

public static String URItoURl(String uri) {

	String url ="";
	String[] uris = uri.split("\\\\");//ӦΪ��\���������ת�·�����һ����\���͵����ǡ�\\��ת������� �����������4��"\"
	for (String  s : uris) {
		if (s.length()>0) {
			url+="/"+s;			
		}
	}	
//	System.out.println("�����·��:"+url);
//	System.out.println("��ȷ·��:"+url.substring(0,url.length()-1));//ȥ�����һ���ַ�
//	System.out.println("�ļ�·��:"+uri);
	return url;
}
/**
 * ������·��ת�������·��
 * @param urlStart ��Ŀ��ʼ·��
 * @param Url	ת��������·��
 * @return	�������·��
 */
public static String getSrc(String urlStart ,String Url) {
	if (urlStart!=null&&Url!=null) {//������Ϊ��
		if (urlStart.equals(Url.subSequence(0, urlStart.length()))) {//
			return Url.subSequence(urlStart.length(),Url.length()).toString();
		}
	}
	return Url;
}

public static void main(String[] args) {
	String uri = "E:\\workspace\\EclipseWorkSpace\\javademo\\public\\picture\\Credentials\\max9640533c-15cb-4e9f-8d89-8084af5db29420151127144552807.jpg";
	String URl="";
	URItoURl(uri);//��������ǽ�·������б�ܴ���ɷ�б�� ��web�����ܷ���
	System.out.println(URl);
	String s = System.getProperty("user.dir");
	System.out.println(System.getProperty("user.dir"));
	System.out.println(getSrc(s,uri));
	System.out.println(URItoURl(getSrc(s,uri)));

	//��Ҫ����Э��
	URl = "http://"+GetIP.GetServiceIP()+":9000"+URItoURl(getSrc(s,uri));
	System.out.println(URl);
	System.out.println(URLtoURI(URl));
	//��ȡ������������IP��ַ-ʵ���Ͽ��ܻ���Ҫ��������IP��ַ
}
}
