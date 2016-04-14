package com.base.other;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CeshiString {
public static void main(String[] args) {
//	String string = "(周1～周5)（共5周） d1，d8，d15 dl、d8、d15、d22、d29、d36 21天为1个周期  每6周为1个周期。   q12h(化疗前ld及dl～d2) 化疗前1天及dl～d2 dl/周，共5周 dl～d5/周共5周 化疗前0.5h";
	String string = "q12h:2.1hb,d1～d2";
//	cut();
//	inData(string);
//	ceshiLang();
	List<String> strlist = new ArrayList();
	strlist.add("q12h:2.1hb,d1～d2");
	strlist.add("q12h:2.1hb,d1～d2");
	strlist.add("q12h:2wb,d1～d2");
	strlist.add("q12h:3wb,d1～d2");
	strlist.add("q12h:3wb,d1～d2");
	strlist.add("q12h:2b,d1～d2");
	strlist.add("q12h:3b,d1～d2");
	checkMax(strlist);
	
//	String old = "周1～周5";
//	String mnew = "w1～w5";
//	replace(string,old,mnew);
	System.exit(0);
}

/**
 * 获取最大的数值
 * @param strlist
 * @return
 */
public static Double  checkMax(List<String> strlist) {

	for (int i = 0; i < strlist.size(); i++) {
		strlist.set(i, getB(strlist.get(i)));
	}
	
	List<String> strlistin = new ArrayList();
	for (String string : strlist) {
		String codenumber = "(\\d?\\.?\\d)";
		String code = "([wh]?b)";
		String whd =  getNumber(string,code);
		if (whd.contains("w")) {
			int number =  Integer.parseInt(getNumber(string,codenumber));
			number = number*7;
			strlistin.add(number+"");
		}else{
			strlistin.add(getNumber(string,codenumber));
		}
		
	}
	Double dnb = 0d;
	for (String string : strlistin) {
		Double fff = Double.parseDouble(string);
		if (fff>dnb) {
			dnb = fff;
		}
	}
	System.out.println("最大的是"+dnb);
	return dnb;
	
}

/**
 * 获取化疗前的相应格式字符
 * @param str
 * @return
 */
public static String  getNumber(String str,String code) {
	Pattern p = Pattern.compile(code);//周1～周5
	Matcher m = p.matcher(str);
	if (m.find()) {
//		System.out.print(str+" #之前# ");
		str = m.group(1);
	}
//	System.out.println(str);
	return str;	
	
}
/**
 * 获取化疗前的相应格式字符
 * @param str
 * @return
 */
public static String  getB(String str) {
	Pattern p = Pattern.compile("(\\d?\\.?\\d+[wh]?b)");//周1～周5
    Matcher m = p.matcher(str);
	if (m.find()) {
//		System.out.println("之前"+str);
		str = m.group(1);
	}
//	System.out.println(str);
	return str;	
	
}

public static void ceshiLang() {
	String str = "测试长度";
	System.out.println(str.length());
	String strs[] = {"测试数组的长度","测试数组位标","对比两者之间的关系"};
	System.out.println(strs.length);
	System.out.println(strs[0]);
	System.out.println(strs[1]);
	System.out.println(strs[2]);
	//结论-数组是从零开始 而length是数组真实个数 两者之间的方式不同 无比较性，但是时不时的还是会出现弄错的情况
}
/**
 * 检查字符串是否有中文
 * @param str
 * @return
 */
	public static String checkstr(String str) {
		str = inData(str);
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]+");//周1～周5
	    Matcher m = p.matcher(str);
		if (m.find()) {
			System.out.println("字符中包含汉字"+str);
			str = inData(str);
		}
		return str;
	}
/**
 * 插入要改变的字符
 * @param str
 * @return
 */
public static String  inData(String str ) {
//	str = test0(str);//周1～周5
	str= str
			.replaceAll("[。]","")//替换掉字符 逗号 顿号 替换为 英文逗号
			.replaceAll("[，、]",",")//替换掉字符 逗号 顿号 替换为 英文逗号
			.replaceAll("[／]","/")//替换掉字符 逗号 顿号 替换为 英文逗号
			.replaceAll("(/周,)","")//替换掉字符 逗号 顿号 替换为 英文逗号
			.replaceAll("[每]","")//替换掉字符 逗号 顿号 替换为 英文逗号
			.replaceAll("(/周)","")//替换掉字符 逗号 顿号 替换为 英文逗号
			.replaceAll("[l]","1")//替换字母L为数字1 如果本身包含字母L的可能会出现ERROR
			.replaceAll("[（）]","")//删除全角括号
			.replaceAll("[\\(\\)]", "");//删除英文括号
//	str = str
	System.out.println(str);
	str = test1(str);//周1～周5
	str = test2(str);//共5周
	str = test3(str);//21天为1个周期
	str = test4(str);//每6周为1个周期
	str = test5(str);//q12h化疗前1天及d1～d2
	str = test6(str);//化疗前1天及dl～d2
	str = test7(str);//化疗前0.5h
		str = test8(str);//化疗前0.5h
	System.out.println(str);
	Pattern p = Pattern.compile("[\u4e00-\u9fa5]+");//周1～周5
    Matcher m = p.matcher(str);
	if (m.find()) {
		System.out.println("字符中包含汉字"+str);
		str = inData(str);
	}
	return null;
	
}
/**
 * 
 * 替换掉相同位置的数据
 * @param string  完整字符串
 * @param old	需要替换的部分
 * @param mnew 更换成新的部分
 * @return
 */
public static String replace(String string,String old,String mnew) {
	
	String str = string.replace(old, mnew);
	System.out.println(str);
	return str;	
}


/**
 * 周1～周5
 * @param str
 * @return
 */
public static String test1(String str) {
//	String str ="周1～周5" ;
	StringBuffer sb = new StringBuffer(); 
	Pattern p = Pattern.compile("周([0-9])～周([0-9])");//周1～周5
    Matcher m = p.matcher(str);
    if (m.find()) {
    	String st = "w"+m.group(1)+"～w"+m.group(2);
    	st=str.replace(m.group(0), st);
//        m.appendReplacement(sb, "w"+m.group(1)+"～w"+m.group(2));
        System.out.println("结果："+st);
//        for (int i = 0; i < m.groupCount()+1; i++) {
//            System.out.println("结果："+i+"#等于#"+m.group(i));
//		} 
        return st;
	}
	return str;	
}
/**
 * 共5周
 * @param str
 * @return
 */
public static String test2(String str) {
//	String str ="（共5周）" ;
	StringBuffer sb = new StringBuffer(); 
	Pattern p = Pattern.compile("共([0-9]+)周");//共5周
	Matcher m = p.matcher(str);
	if (m.find()) {
		String st = ":"+m.group(1)+"w";
		st=str.replace(m.group(0), st);
		System.out.println("结果："+st);
//		for (int i = 0; i < m.groupCount()+1; i++) {
//			System.out.println("结果："+i+"#等于#"+m.group(i));
//		} 
		return st;
	}
	return str;	
}
/**
 * 21天为1个周期
 * @param str
 * @return
 */
public static String test3(String str) {
//	String str ="21天为1个周期" ;
	StringBuffer sb = new StringBuffer(); 
	Pattern p = Pattern.compile("(\\d+)天为(\\d+)[个]?周期");//21天为1个周期
	Matcher m = p.matcher(str);
	if (m.find()) {
		String st = m.group(1)+"d:"+m.group(2)+"c";
		st=str.replace(m.group(0), st);
		System.out.println("结果："+st);
		return st;
	}
	return str;	
}
/**
 * "每6周为1个周期。"
 * @param str
 * @return
 */
public static String test4(String str) {
//	String str ="每6周为1个周期" ;
	StringBuffer sb = new StringBuffer(); 
	Pattern p = Pattern.compile("[每]?([0-9]+)周为([0-9]+)个周期");//每6周为1个周期
	Matcher m = p.matcher(str);
	if (m.find()) {
		String st = m.group(1)+"w:"+m.group(2)+"c";
		st=str.replace(m.group(0), st);
		System.out.println("结果："+st);
		return st;
	}
	return str;	
}
/**
 * "q12h(化疗前ld及dl～d2)"
 * @param str
 * @return
 */
public static String test5(String str) {
//	String str ="q12h(化疗前ld及dl～d2)" ;
	StringBuffer sb = new StringBuffer(); 
	Pattern p = Pattern.compile("(化疗前)([0-9]+)d及");//化疗前X天
	Matcher m = p.matcher(str);
	if (m.find()) {
		String st = ":"+m.group(2)+"b,";
		st=str.replace(m.group(0), st);
		System.out.println("结果："+st);
		return st;
	}
	return str;	
}
/**
 * 化疗前1天及dl～d2
 * @param str
 * @return
 */
public static String test6(String str) {
//	String str ="化疗前1天及dl～d2" ;
	StringBuffer sb = new StringBuffer(); 
	Pattern p = Pattern.compile("(化疗前)([0-9]+)天及");//化疗前1天及dl～d2
	Matcher m = p.matcher(str);
	if (m.find()) {
		String st = m.group(2)+"b,";
		st=str.replace(m.group(0), st);
		System.out.println("结果："+st);
		return st;
	}
	return str;	
}
/**
 * "化疗前0.5h"
 * @param str
 * @return
 */
public static String test7(String str) {
//	String str ="q12h(化疗前ld及dl～d2)" ;
	StringBuffer sb = new StringBuffer(); 
	Pattern p = Pattern.compile("化疗前(\\d*\\.?\\d*)h");//化疗前0.5h
	Matcher m = p.matcher(str);
	if (m.find()) {
			String st = " "+m.group(1)+"hb:";
		st=str.replace(m.group(0), st);
		System.out.println("结果："+st);
		return st;
	}
	return str;	
}
	/**
	 * "连服3周d1～d21"
	 * @param str
	 * @return
	 */
	public static String test8(String str) {
//		String str ="q12h(化疗前ld及dl～d2)" ;
		StringBuffer sb = new StringBuffer(); 
		Pattern p = Pattern.compile("连服(\\d+)周");//化疗前0.5h
		Matcher m = p.matcher(str);
		if (m.find()) {
			String st = " "+m.group(1)+"w";
			st=str.replace(m.group(0), st);
			System.out.println("结果："+st);
			return st;
		}
		return str;	
	}

/**
 * 测试多分割符号
 */
public static void cut() {
	String str = "（共50周）周1～周5,q12h dl～d5,21天为1个周期,连服4周，6周为1个周期,d1～d14，3周为1个周期,dl、d8、d15、d22、d29、d36";
	String strs[] = str.split("[,、，]");
	for (String string : strs) {
//		String matching[] ={"(周)([0-9])(～)(周)([0-9])","共[*]周+","[0-9]周为[0-9]个周期","[化疗前]+","[q[0-9]+h]+"} ;
		String matchings[][] = {{"周([0-9])～周([0-9])","1","2"}//周1～周5 w1～w5
//		,{"(q)([0-9]+)(h)","1"}//q12h q12h: 
		,{"(共)([0-9]+)(周)","2"}//共50周 50w
		,{"([0-9]+)(天为)([0-9]+)(个周期)","1","3"}//21天为1个周期 21d:1c
		,{"([0-9]+)(周为)([0-9]+)(个周期)","1","3"}//6周为1个周期 6w:1c
		,{"(q)([0-9]+)(h).(化疗前)([0-9]+)(d)","1","2","3","4"}//q12h(化疗前1d及dl～d2) q12h:1b,dl～d2 1+2+3+":"+5+"d"
		,{"化疗前([1-9]\\d*\\.?\\d*)|(0\\.?\\d*[1-9])h","0"}//化疗前0.5h 0.5hb 0+"b"
		,{"d(\\d*)～d(\\d*)/周","1","2"}//d1～d5/周共5周 wl～w5:5w "w"+1+"～w"+2+":"
		,{"d(\\d*)/周","1"}//d1/周 w1: "w"+1+":"
		,{"([0-9]+)(周)","1"}//5周 4w
		,{"(前)([0-9]+)(天)","2"}//5周 1b
		};
		
		for (String[] matching : matchings) {
			for (String strr : matching) {
			//正则皮牌
			System.out.println(string+"--"+strr);
			StringBuffer sb = new StringBuffer(); 
			Pattern p = Pattern.compile(matching[0]);
	        Matcher m = p.matcher(string);
	        if (m.find()) {
	            m.appendTail(sb);
	            System.out.println("结果："+sb.toString());
			}
		}
		
	}
}
}
}
