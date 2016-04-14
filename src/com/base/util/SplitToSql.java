package com.base.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.base.mysql.Hospital;
import com.base.mysql.HospitalDao;
import com.base.mysql.Region;
import com.base.mysql.RegionDao;
import com.base.mysql.TestUser;

public class SplitToSql {
public static void main(String[] args) {
//	RegionDao regiondao = new RegionDao();
//	Region region = regiondao.loadByRetionName("武安市","邯郸市");
//	System.out.println(region);
	readDate();//从文本读取医院信息数据写入到sql中
//	ceshi();
}
/**
 * 分割每条数据 筛选title
 * @param string
 */
public static int	spiltTitle(String string) {
	if (string.length()>0) {
		String[] str = string.split(",");
		String[] str1 = str[0].split("医院列表");
		//把title数据处理的干净一些
		for (int i = 1; i < str1.length; i++) {
			if (str1[i].trim().contains(str1[i-1].trim())) {

				String strB = str1[i].trim();
				String strA = str1[i-1].trim();
				str1[i] = strB.substring(strA.length(), strB.length());
			}
		}
		List<String[]> lidtr = new ArrayList<String[]>();
		RegionDao regiondao = new RegionDao();
		String update ="";
			//获取区域代码
		String str1B = str1[str1.length-1].trim();
		String str1A = str1[str1.length-2].trim();
		String address = null;
		if (str1B.contains("海东地区乐都县")) {
			System.out.println("");
		}
			if (str1B.contains(str1A)) {
				address = str1B.substring(str1A.length(),str1B.length());//区县级地区名
			}else{
				address = str1B;
				
			}
				Region region = regiondao.loadByRetionName(address.trim(),str1A);
				if (region==null) {//如果没有该区域就不执行下面的了
						
//						for (int i = 0; i < listRegion.size(); i++) {
//							if (listRegion.get(i).getHasSubRegion().equals("N")
//									&&listRegion.get(i).getRetionName().contains(address)) {
//								System.out.println("异常但仍旧找到相似的文字的区域"+address);
//								region = listRegion.get(i);
//							}
//						}
						if (region==null) {//如果执行了上面的代码都还是没有数据 就返回
							WriteText.appendWrite("\n" +LinksDate0.time()+ address, "c:\\errorsave.txt");
							return -1;							
						}
				}
				lidtr.add(new String[]{"region_code",region.getRegionCode()});//获取区域代码
			
			lidtr.add(new String[]{"hospital_name",str[1]});//得到医院名
			for (int i = 2; i < str.length; i++) {

				String[] detail = str[i].split("：");
				String[] detaildate = {"",""};
				for (int j = 0; j < detail.length; j++) {
					if (j==0) {
						detaildate[0] =detail[j];
					}else{
						detaildate[1] +=detail[j];
					}
				}
				if (str1.length>1) {
					if (detaildate[0].equals("医院地址")) {	
						lidtr.add(new String[]{"address",detaildate[1]});
					} else if (detaildate[0].equals("联系电话")){
						lidtr.add(new String[]{"hospital_tel",detaildate[1]});
					} else if (detaildate[0].equals("医院等级")){
						lidtr.add(new String[]{"hospital_grade",detaildate[1]});
					} else if (detaildate[0].equals("经营方式")){
						lidtr.add(new String[]{"mode_of_operation",detaildate[1]});
					} else if (detaildate[0].equals("重点科室")){
						lidtr.add(new String[]{"key_departments",detaildate[1]});
					} else if (detaildate[0].equals("电子邮箱")){
						lidtr.add(new String[]{"e_mail",detaildate[1]});
					} else if (detaildate[0].equals("医院网站")){
						lidtr.add(new String[]{"hospital_website",detaildate[1]});
					} else if (detaildate[0].equals("传真号码")){
					} 
				}
			}
			String id = UUID.randomUUID().toString();
			lidtr.add(new String[]{"id",id});
			TestUser.addHospitalSql(lidtr);
			HospitalDao hospitaldao = new HospitalDao();
			Hospital hospital = hospitaldao.loadById(id);
			if (hospital.getRegionCode()==null) {
				System.out.println("区域代码为空");
			}
//			System.out.println(hospital);
		}
	return 0;
}

/**
 * 分割每条数据 3W条数据
 * @param string
 */
public static void	spiltRow(String string) {
	if (string.length()>0) {
		String[] str = string.split(",");
		for (String string2 : str) {//读取被分割的数据
			String[] str1 = string2.split("：");
			if (str1.length==1) {//获取医院名称
				System.out.println(str1);
			}
			
			String head = "";
			String other = "";
			for (int i = 0; i < str1.length; i++) {
				if (i==0) {
					head = str1[i];
				}  else {
					other += str[i];
				}				
			}
			
			
			System.out.println(string2);
		}
	}
}
/**
 * 从文本读取数据医院信息
 */
public static void readDate() {
	List<String> strlist = WriteText.readTxtFile("c:\\hospitalDate.txt");
	for (String string : strlist) {//读取行数据
//		spiltRow(string);
		if (string.length()>0) {
			spiltTitle(string);			
		}
	}
}
/**
 * 从文本读取行政区域数据
 * 返回区域代码
 */
public static String readRegion(String str) {
	List<String> strlist = WriteText.readTxtFile("c:\\行政区域码表.txt");
	for (String string : strlist) {//读取行数据
		if (string.contains(str)) {
			string=string.trim();
				String[] s =string.split("(?=[a-z]*[a-z])(?<=[0-9]*[0-9])|(?=[\\u4e00-\\u9fa5]*[\\u4e00-\\u9fa5])");
				return s[0].trim();
		}
	}
	//写入到错误代码日志中
	WriteText.appendWrite("\n" +LinksDate0.time()+str, "c:\\errorsave.txt");
	return "暂时无代码";
}
}
