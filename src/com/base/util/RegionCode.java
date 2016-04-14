package com.base.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.base.mysql.Hospital;
import com.base.mysql.HospitalDao;
import com.base.mysql.Region;
import com.base.mysql.RegionDao;
import com.base.mysql.TestUser;


public class RegionCode {
	public static void main(String[] args) {
//		insetr();//插入区域代码
//		connection();//整理区域代码
//		delSqlRedundant();//删除无意义的区域代码
		delnull();
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

		List<String[]> lidtrs = readRegion("c:\\行政区域码表 [完整版本].txt");
		for (String[] strings : lidtrs) {
			List<String[]> lidtr = new ArrayList<String[]>();

			lidtr.add(new String[]{"region_code",strings[0].trim()});
			lidtr.add(new String[]{"retion_name",strings[1].trim()});
			TestUser.addRegionSql(lidtr);
			RegionDao regiondao = new RegionDao();
			Region region = regiondao.loadById(strings[0]);
			System.out.println(region.getRetionName());
		}
		

	}
	/**
	 * 从文本读取行政区域数据
	 * 返回区域代码
	 */
	public static List<String[]> readRegion(String uri) {
		List<String[]> lidtr = new ArrayList<String[]>();
//		List<String> strlist = WriteText.readTxtFile("c:\\行政区域码表new.txt");
		List<String> strlist = WriteText.readTxtFile(uri);
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
		String value = "'市','县','市辖区','市区'";//被删除的区域关键字
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
	/**
	 * 检测医院区域码
	 */
	public static void checkhospital() {
		for (int i = 0;;i++) {
			List<Hospital> hospitalList = new HospitalDao().selectSQL("region_code","LIKE","______"," GROUP BY region_code LIMIT "+i+","+1);
			if (hospitalList.size()==0) {
				break;
			}
			for (Hospital hospital : hospitalList) {
				List<Region> regionlistcilys1 = new RegionDao().selectSQL("LEVEL","=","3"," AND region_code LIKE "+hospital.getRegionCode());
				if (regionlistcilys1.size()>0) {
//					List<Region> regionlis3 = new RegionDao().selectSQL("LEVEL","=","3");
//					for (Region region : regionlistcilys1) {
//						String str = region.getRetionName();
//						if (region.getParentRegionCode()==null) {
//							continue;
//						}
//						List<Region> region1 = new RegionDao().selectSQL("region_code","=",region.getParentRegionCode());
//						if (region1.size()>0&&region1.get(0).getParentRegionCode()!=null) {
//							str = region1.get(0).getRetionName()+str;
//							System.out.println(region1.get(0).getParentRegionCode());
//							List<Region> region2 = new RegionDao().selectSQL("region_code","=",region1.get(0).getParentRegionCode());
//							if (region2.size()>0) {
//								str = region2.get(0).getRetionName()+str;
//							}
//							if (hospital.address.contains(str)) {
//								List<Hospital> hospitas = new HospitalDao().selectSQL("region_code","LIKE",hospital.getRegionCode());
//								for (Hospital hospita : hospitas) {
//									hospita.setRegionCode(region.regionCode);
//									System.out.println(hospita.regionCode+"-"+hospital.address+"#"+str);
//									new HospitalDao().update(hospita);
//								}
//							}
//						}
//						
//					}
				}else{
					String str = "";
					for (int j = 0; j < hospitalList.get(0).address.length()-2; j++) {	
						for (int j2 = j+2; j2 < hospitalList.get(0).address.length()-1; j2++) {
							str += "\""+hospitalList.get(0).address.subSequence(j, j2)+"\",";
						}
					}
					
					str = str.subSequence(0, str.length()-1)+"";
					System.out.println(str);
					
					for (int j = 0;  ; j++) {
						List<Region> regionlist1 = new RegionDao().selectSQL("retion_name","like ","%"," and retion_name in ("+str+")");
						for (Region region : regionlist1) {
							System.out.println(region.retionName+region.regionCode);
						}
					}
				}
			}
			
		}
	}
	/**
	 * 去掉没有子区域
	 * 和没有医院数据的区域
	 */
	public static void delnull() {
		checkhospital();
		List<Region> regionlistcilys1 = new RegionDao().selectSQL("LEVEL","=","1");
		
//		regionlistcilys1.addAll(regionlistcilys2);
		for (Region region : regionlistcilys1) {
			List<Region> regionlistcilys = new RegionDao().selectSQL("parent_region_code","LIKE",region.getRegionCode());
			if (regionlistcilys.size()==0) {
				System.out.println(region);
				new RegionDao().delete(region);
			}
		}
		List<String> value = new ArrayList(Arrays.asList("市","县","市辖区","市区","新区","郊区"));//被删除的区域关键字
		List<String[]> lidtrs = readRegion("c:\\行政区域码表new.txt");
		for (String[] strings : lidtrs) {
				Region regionlin = new RegionDao().loadById(strings[0]);
				List<Hospital> hospitalList = new HospitalDao().selectSQL("address","LIKE","%"+strings[1].trim()+"%");
				if (regionlin!=null||value.contains(strings[1])||hospitalList.size()==0) {
				continue;//已有数据
				}
				String str = strings[0].substring(0, 4);
				List<Region> regionlistcilys2 = new RegionDao().selectSQL("LEVEL","=","2");
				System.out.println(1);
			for (Region region : regionlistcilys2) {
				String str1 = region.getRegionCode().substring(0, 4);
				if (str.equals(str1)) {
					if (!strings[1].equals(region.retionName)) {
						List<String[]> lidtr = new ArrayList<String[]>();
						lidtr.add(new String[]{"region_code",strings[0].trim()});
						lidtr.add(new String[]{"retion_name",strings[1].trim()});
						lidtr.add(new String[]{"parent_region_code",region.getRegionCode()});
						TestUser.addRegionSql(lidtr);
						RegionDao regiondao = new RegionDao();
						Region regions = regiondao.loadById(strings[0]);
						System.out.println(regions.getRetionName());
					}
				}
			}
		}
		List<Region> regionlistcilys3 = new RegionDao().selectSQL("LEVEL","=","3");
		for (Region region : regionlistcilys3) {

			List<Hospital> hospitalList = new HospitalDao().selectSQL("region_code","LIKE",region.getRegionCode());
			if (hospitalList.size()==0) {
				System.out.println(region);
				new RegionDao().delete(region);
			}
		}
	}
}
