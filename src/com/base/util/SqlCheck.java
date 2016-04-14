package com.base.util;

import java.util.ArrayList;
import java.util.List;

import com.base.mysql.Hospital;
import com.base.mysql.HospitalDao;
import com.base.mysql.Region;
import com.base.mysql.RegionDao;
/**
 * 删除数据库中重复的数据
 * @author Administrator
 *
 */
public class SqlCheck {

	private static List<String> hospitalListold = new ArrayList();
	public static void main(String[] args) {
//		selectSql();
//		checkRegion();
//		checkDelsql();
//		checkDateAll();
		checkcode();
		System.exit(0);
	}
	
	/**
	 * 查询数据
	 */
	public static void selectSql() {
		List<Hospital> hospitalList = new HospitalDao().selectSQL("region_code","LIKE","____00");
		for (Hospital hospital : hospitalList) {
			if (!hospitalListold.contains(hospital.getHospitalName())) {
				selectCheck(hospital);
			}			
			
		}
	}
	
	/**
	 * 检测数据书否存在重复
	 */
	public static void selectCheck(Hospital onedate) {
		List<Hospital> hospitalList = new HospitalDao().selectSQL("hospital_name","LIKE",onedate.HospitalName);
		for (Hospital hospital : hospitalList) {
			String str = hospital.getRegionCode().subSequence(4,6).toString();
			if (!str.equals("00")&&!hospitalListold.contains(hospital.getHospitalName())&&hospitalList.size()>1) {//如果有小一个级别的数据，就删除掉带00的区域码数据
				for (Hospital hospital0 : hospitalList) {
					if (hospital.getId()!=hospital0.getId()) {
						deleteSql(hospital0);
						hospitalListold.add(hospital0.getHospitalName());
					}
				}
			}
		}
	}
	/**
	 * 删除数据
	 */
	public static void deleteSql(Hospital onedate) {
		System.out.println("删除数据"+onedate.getHospitalName());
		new HospitalDao().delete(onedate);
	}
	/**
	 * 待增加功能
	 * 1.检测医院地址与区域代码是否匹配 如北京东城区
	 * 2.删除没有使用的旧区域代码
	 */
	/**
	 * 更正区域代码尾数为00的数据
	 */
	public static void checkRegion() {
		List<Hospital> hospitalList = new HospitalDao().selectSQL("region_code","LIKE","____00");
		for (Hospital hospital : hospitalList) {
			for (int i = 1,k= 0; i <= hospital.getAddress().length()&&hospital.getAddress().length()>0; i++) {
				if (k>i) {
					continue;
				}
				try {
					hospital.getAddress().subSequence(k, i).toString();
				} catch (Exception e) {
					// TODO: handle exception
				e.printStackTrace();
				}
				String codecily = hospital.getAddress().subSequence(k, i).toString();
				List<Region> regionlistcilys = new RegionDao().selectSQL("retion_name","=",codecily);	
				if (regionlistcilys!=null&&regionlistcilys.size()>0) {
					for (Region region : regionlistcilys) {
							k +=codecily.length();
							WriteText.appendWrite("\n"+region.regionCode+";区域:"+region.getRetionName()+";地址:"+hospital.address,"c:\\checksql.txt");//追写入文件中
							System.out.println(region.regionCode+";区域:"+region.getRetionName()+";地址:"+hospital.address);
						 if (region.getRetionName().equals(codecily)&&!region.getRegionCode().subSequence(4, 6).equals("00")){
							hospital.setRegionCode(region.getRegionCode());
							System.out.println("医院:"+hospital.getHospitalName()+";区域码:"+hospital.regionCode+";区域:"+region.getRetionName()+";地址:"+hospital.address);
							new HospitalDao().update(hospital);
						}
					}
				}
			}
//			String codecily = hospital.getRegionCode().subSequence(0, 2).toString();
//			String code = hospital.getRegionCode().subSequence(0, 4).toString();
//			List<Region> regionlistcily = new RegionDao().selectSQL("region_code","LIKE",code+"__");
//			List<Region> regionlist = new RegionDao().selectSQL("region_code","LIKE",code+"__");
//			for (Region regioncily : regionlistcily) {
//			for (Region region : regionlist) {
//				if (hospital.getHospitalName().contains(region.getRetionName())) {
//					
//				}
//			}
//			}
		}
	}
	/**
	 * 删除没有没有医院的区域
	 */
	public static void checkDelsql() {
		List<Region> regionlistcilys = new RegionDao().selectSQL("LEVEL","=","3");
		int i= 0;
		for (Region region : regionlistcilys) {
			List<Hospital> hospitalList = new HospitalDao().selectSQL("region_code","LIKE",region.getRegionCode());
			List<Hospital> hospitalLists = new HospitalDao().selectSQL("region_code","LIKE",region.getRetionName());
			if (hospitalList.size()==0) {
				System.out.println(region.getRetionName()+"-"+region.getRegionCode());
				new RegionDao().delete(region);
			}
			System.out.println(i++);
		}
	}
	
	/**
	 * 检测数据成都市单独处理
	 */
	public static void checkdateCD() {
		String [] str = new String[]{"武侯区","锦江区","青羊区","金牛区","成华区","龙泉驿区","温江区","新都区","青白江区","双流区","郫县","蒲江县","大邑县","金堂县","新津县","都江堰市","彭州市","邛崃市","崇州市"};
		for (String string : str) {
			
			List<Region> regionlistcilys = new RegionDao().selectSQL("retion_name","=",string);
			if(regionlistcilys.size()==0) {
				continue;
			}
			for (Region region : regionlistcilys) {
				if (regionlistcilys.size()>1) {
					System.out.println(string+"-有重复"+region.getRegionCode());
				}
				List<Hospital> hospitalList = new HospitalDao().selectSQL("region_code","LIKE",region.getRegionCode());
				if (hospitalList.size()>0) {
					System.out.println(string+"-正常");
				}else{
					System.out.println("-异常-"+string);
				}
			}
		}
//		List<Region> regionlistcilys = new RegionDao().selectSQL("region_code","LIKE","5101__");
//		for (Region region : regionlistcilys) {
//			List<Hospital> hospitalList = new HospitalDao().selectSQL("region_code","LIKE",region.getRegionCode());
//			if (hospitalList.size()>0) {
//				System.out.println("有   "+region.getRegionCode()+"-"+region.getRetionName());
//			}else{
//				System.out.println("无   "+region.getRegionCode()+"-"+region.getRetionName());
//			}
//		}
	}
	/*
	 * 检测所有医院数据是否正确
	 */
	public static void  checkDateAll() {
//		List<Region> regionlistcilys = new RegionDao().selectSQL("region_code","LIKE","______","LIMIT "+0+","+20);
		List<Hospital> hospitalList = new HospitalDao().selectSQL("region_code","LIKE","______","LIMIT "+0+","+1);
		int i = 0;
		int k = 0;
		while (hospitalList.size()!=0) {
			i = k;
			k += 20;
			hospitalList = new HospitalDao().selectSQL("region_code","LIKE","______","LIMIT "+i+","+k);
			for (Hospital hospital : hospitalList) {
				List<Region> regionlistcilys = new RegionDao().selectSQL("region_code","LIKE",hospital.getRegionCode());
				for (Region region : regionlistcilys) {
					int n = hospital.getAddress().indexOf(region.getRetionName());
					if (n>1) {
						String str = hospital.getAddress().subSequence(0, n).toString();
						if (region.getParentRegionCode()==null) {
							System.out.println(region.getRetionName()+"-没有父区域-"+region.getRegionCode());
							continue;
						}
						List<Region> regioncily = new RegionDao().selectSQL("region_code","LIKE",region.getParentRegionCode());
						for (Region region2 : regioncily) {
							if (str.contains(region2.getRetionName())) {
//								System.out.print("【对】");
								if (region2.getParentRegionCode()==null) {
									continue;
								}
								List<Region> regioncilys = new RegionDao().selectSQL("region_code","LIKE",region2.getParentRegionCode());
								for (Region region3 : regioncilys) {
									if (str.contains(region3.getRetionName())) {
//										System.out.print("【对】");
									}else{
										System.out.println("【 异常】"+hospital.getHospitalName()+"#####区域代码@"+hospital.getRegionCode()+"#####区域名"+region3.getRetionName());
									}
								}
							}else{
								System.out.println("【数据异常】"+hospital.getHospitalName()+"#####区域代码@"+hospital.getRegionCode()+"#####区域名"+region2.getRetionName());
							}	
						}
//						List<Region> regions = new RegionDao().selectRegion("retion_name",str);
						
					}else{
						System.out.println("医院所属区域异常@"+hospital.getHospitalName() +"#####区域代码@"+hospital.getRegionCode()+"#####区域名"+region.getRetionName());
					}
					
					
				}
//				if (regionlistcilys.size()>1) {
//					System.out.println(hospital.getHospitalName());
//					System.out.println(hospital.getRegionCode());
//				}
//				System.out.println(hospital.getHospitalName());
			}
		}
		System.out.println("检测结束");
	}
	/**
	 * 检测医院数据是否匹配
	 */
	public void checkCode() {
		
		
	}
	/**
	 * 检测医院地区关联是否正确
	 */
	public static void checkcode() {
		List<Region> regionlistcilys = new RegionDao().selectSQL("region_code","LIKE","______");
		Boolean s = true;
		int i = 0 ;
		while (s&&regionlistcilys.size()!=0) {
			regionlistcilys = new RegionDao().selectSQL("region_code","LIKE","______"," ORDER BY LEVEL LIMIT "+i+++", 1");
//			System.out.println(regionlistcilys.get(0).retionName);
			for (Region region : regionlistcilys) {
				List<Region> regionlist = new RegionDao().selectSQL("retion_name","LIKE","%"+region.retionName+"%");
				
				if (regionlist.size()>1) {
//					if (regionlist.get(0).retionName.equals("唐山地区")||regionlist.get(1).retionName.equals("唐山地区")) {
//						System.out.println("");
//					}
					checkson(regionlist);
					for (Region region2 : regionlist) {						
//						checknull(region2);
//						
					}
				}
				
			}
		}
	}
	/**
	 * 检测有无子集
	 * 将没有子集的数据转移到有子集的子集数据上
	 */
	public static void checkson(List<Region> regionlist) {
		if (regionlist.get(0).retionName.contains("省直辖县级行政区划")&&
				regionlist.get(0).level!="3"&&
				regionlist.get(1).level!="3") {
			
		}else
		if (
				(regionlist.get(0).retionName.equals(regionlist.get(1).retionName)||
						regionlist.get(0).retionName.contains("地区")||
						regionlist.get(1).retionName.contains("地区"))) {
			System.out.println(regionlist.get(0).retionName+"##"+regionlist.get(1).retionName);
			List<Region> regionA = new RegionDao().selectSQL("region_code","LIKE",regionlist.get(0).getParentRegionCode());
			List<Region> regionB = new RegionDao().selectSQL("region_code","LIKE",regionlist.get(1).getParentRegionCode());
			if (regionA.size()>0&&regionA.get(0).retionName.contains("地区")) {
				List<Hospital> hospitalListZA = new HospitalDao().selectSQL("region_code","LIKE",regionlist.get(0).getRegionCode());
				for (Hospital hospital : hospitalListZA) {
					List<Region> regionAs = new RegionDao().selectSQL("parent_region_code","LIKE",regionlist.get(0).getRegionCode());
					if (regionAs.size()>0) {
						for (Region region : regionAs) {
							if (hospital.address.contains(region.getRetionName())) {
								hospital.regionCode = region.getRegionCode();
								System.out.println(hospital.getHospitalName());
								WriteText.appendWrite("\n将医院数据#"+hospital.getHospitalName()+" "+regionlist.get(0).getRegionCode()+";改成:"+hospital.regionCode+";地址:"+hospital.address,"c:\\checksql.txt");//追写入文件中
								new HospitalDao().update(hospital);
								continue;
							}
						}
					}else{
						hospital.regionCode = regionlist.get(1).getRegionCode();
						System.out.println(hospital.getHospitalName());
						WriteText.appendWrite("\n将医院数据#"+hospital.getHospitalName()+" "+regionlist.get(0).getRegionCode()+";改成:"+hospital.regionCode+";地址:"+hospital.address,"c:\\checksql.txt");//追写入文件中
						new HospitalDao().update(hospital);
					}
					
				}

				WriteText.appendWrite("\n删除区域#"+regionA.get(0).getRetionName()+" "+regionA.get(0).getRegionCode(),"c:\\checksql.txt");//追写入文件中
				new RegionDao().delete(regionA.get(0));
			}else if (regionB.size()>0&&regionB.get(0).retionName.contains("地区")) {
				
					
				
				List<Hospital> hospitalListZB = new HospitalDao().selectSQL("region_code","LIKE",regionlist.get(1).getRegionCode());
				for (Hospital hospital : hospitalListZB) {
					List<Region> regionBs = new RegionDao().selectSQL("parent_region_code","LIKE",regionlist.get(0).getRegionCode());
					if (regionBs.size()>0) {
						for (Region region : regionBs) {
							if (hospital.address.contains(region.getRetionName())) {
								hospital.regionCode = region.getRegionCode();
								WriteText.appendWrite("\n将医院数据#"+hospital.getHospitalName()+" "+regionlist.get(1).getRegionCode()+";改成:"+hospital.regionCode+";地址:"+hospital.address,"c:\\checksql.txt");//追写入文件中
								System.out.println(hospital.getHospitalName());
								new HospitalDao().update(hospital);
								continue;
							}
						}
					}else{
						hospital.regionCode = regionlist.get(0).getRegionCode();
						WriteText.appendWrite("\n将医院数据#"+hospital.getHospitalName()+" "+regionlist.get(1).getRegionCode()+";改成:"+hospital.regionCode+";地址:"+hospital.address,"c:\\checksql.txt");//追写入文件中
						System.out.println(hospital.getHospitalName());
						new HospitalDao().update(hospital);
					}
					
					
				}
				System.out.println(regionB.get(0).retionName);
				WriteText.appendWrite("\n删除区域#"+regionB.get(0).getRetionName()+" "+regionB.get(0).getRegionCode(),"c:\\checksql.txt");//追写入文件中
				new RegionDao().delete(regionB.get(0));
			}
		}
		
		
	}
	/**
	 * 检测有没有子区域或医院数据
	 */
	public static void checknull(Region region) {
		List<Hospital> hospitalList = new HospitalDao().selectSQL("region_code","LIKE",region.regionCode);
		if (hospitalList.size()>0) {
//			System.out.println("有数据-"+region.regionCode+" ## "+region.retionName);
		}else{
			
			List<Region> regions = new RegionDao().selectSQL("parent_region_code","LIKE",region.getRegionCode());
			if (regions.size()==0) {
				System.out.println("无-"+region.regionCode+" ## "+region.retionName+"#级别= "+region.level);
				new RegionDao().delete(region);
			}
			//有没有子区域
			for (Region region3 : regions) {
				checknull(region3);
			}
		}
	}
}
