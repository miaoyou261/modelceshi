package com.base.util;

import java.util.ArrayList;
import java.util.List;

import com.base.mysql.Region;
import com.base.mysql.RegionDao;
import com.base.mysql.TestUser;


public class RegionCode {
	public static void main(String[] args) {
//		insetr();//插入区域代码
//		connection();//整理区域代码
		delSqlRedundant();//删除无意义的区域代码
	}
	/**
	 * 整理区域代码对应关系
	 */
	public static void connection() {
		RegionDao regiondao = new RegionDao();
		List<Region> list = regiondao.listRegion();
		for (Region u : list) {
			String  update = "region_code='"+u.getRegionCode()
					+"',retion_name='"+u.getRetionName();
			String code = u.getRegionCode();//获取区域代码
			if(code.subSequence(code.length()-4, code.length()).equals("0000")){//省级
				update+="',level='1";//级别
				update+="',has_sub_region='Y";//
			}else if(code.subSequence(code.length()-2, code.length()).equals("00")){//市级
				update+="',level='2";//级别
				update+="',parent_region_code='"+code.subSequence(0, 2)+"0000";//级别
				update+="',has_sub_region='Y";//
			}else {//区级或县市级
				update+="',level='3";//级别
				update+="',parent_region_code='"+code.subSequence(0, 4)+"00";//级别
				update+="',has_sub_region='N";//
			}

			update+="' where region_code='"+code+"'";
			TestUser.updateRegionSql(update);
		System.out.println(u);
		}
	}
	/**
	 * 从文本中插入区域代码数据
	 */
	public static void insetr() {

		List<String[]> lidtrs = readRegion();
		for (String[] strings : lidtrs) {
			List<String[]> lidtr = new ArrayList<String[]>();

			lidtr.add(new String[]{"region_code",strings[0].trim()});
			lidtr.add(new String[]{"retion_name",strings[1].trim()});
			TestUser.addRegionSql(lidtr);
			RegionDao regiondao = new RegionDao();
			Region region = regiondao.loadByRetionName(strings[1],null);
			System.out.println(region);
		}
		

	}
	/**
	 * 从文本读取行政区域数据
	 * 返回区域代码
	 */
	public static List<String[]> readRegion() {
		List<String[]> lidtr = new ArrayList<String[]>();
		List<String> strlist = WriteText.readTxtFile("c:\\行政区域码表new.txt");
		for (String string : strlist) {//读取行数据
				string=string.trim();
					String[] s =string.split(" ");
					String[] n = {s[0],s[s.length-1].trim()};
					lidtr.add(n);
		}
		return lidtr;
	}
	
	/**
	 * 删除数据库多余的数据
	 * 如 【县 市辖区 市区】
	 */
	public static void delSqlRedundant() {
		RegionDao regiondao = new RegionDao();
		String value = "'县','市辖区','市区'";//被删除的区域关键字
		List<Region> regionlist = regiondao.selectRegion("retion_name",value);
		for (Region region : regionlist) {
			List<Region> regionChildlist = regiondao.selectRegion("parent_region_code",region.getRegionCode());
			for (Region regionChild : regionChildlist) {
				regionChild.setParentRegionCode(region.getParentRegionCode());
				String  update = "parent_region_code='"+regionChild.getParentRegionCode();
				update+="' where region_code='"+regionChild.getRegionCode()+"'";
				TestUser.updateRegionSql(update);
			}
			//删除这条数据
			regiondao.delete(region);
		}
		
	}
}
