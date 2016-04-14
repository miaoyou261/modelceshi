package com.base.mysql;

import java.util.List;

public class TestUser {
public static void main(String[] args) {
//	ceshiHospitalSql();//用SQL方式添加
//	ceshiHospital();//添加医院信息
//	ceshiRegion();//更改区域代码	
//	ceshiUser();//测试USER
}

public static void addHospitalSql(List<String[]> lidtr) {	
	String name = "";//字段
	String values = "";//值
	for (String[] strings : lidtr) {
		if (strings.length>0&&strings[0].length()>0&&strings[1].length()>0) {
			name += strings[0]+",";
			values += "'"+strings[1]+"',";			
		}
	}
	name = name.substring(0, name.length()-1);
	values = values.substring(0, values.length()-1);
	String sql = "INSERT INTO hospital("+name+") VALUES ("+values+")";
	HospitalDao hospitaldao = new HospitalDao();
	hospitaldao.addSql(sql);//添加用户
	// userDao.deleteById(1);// 删除Id为1的用户
	// userDao.update(new User(2, "李四", "1234546"));// 更新Id为2的用户信息
	// System.out.println(userDao.loadById(2)); // 查询Id为2的用户信息
	// 列出所有的用户
//	List<Hospital> list = hospitaldao.listHospital();
//	for (Hospital u : list) {
//	System.out.println(u);
//	}
	
}
public static void ceshiHospital() {
	Hospital hospital = new Hospital();
	hospital.setId("123456");//用户ID
	hospital.setHospitalName("123456");//医院名称
	hospital.setRegionCode("123456");//区域代码
	hospital.setAddress("123456");//医院地址
	hospital.setHospitalTel("123456");//医院电话
	hospital.setHospitalGrade("123456");//医院等级
	hospital.setKeyDepartments("123456");//重点科室
	hospital.setModeOfOperation("123456");//经营方式
	hospital.seteMail("123456");//电子邮箱
	hospital.setHospitalWebsite("123456");//医院网站
	hospital.setOrdernb(0);//顺序
	HospitalDao hospitaldao = new HospitalDao();
	hospitaldao.add(hospital);//添加用户
	// userDao.deleteById(1);// 删除Id为1的用户
	// userDao.update(new User(2, "李四", "1234546"));// 更新Id为2的用户信息
	// System.out.println(userDao.loadById(2)); // 查询Id为2的用户信息
	// 列出所有的用户
	List<Hospital> list = hospitaldao.listHospital();
	for (Hospital u : list) {
	System.out.println(u);
	}
	
}
public static void addRegionSql(List<String[]> lidtr) {	
	RegionDao regiondao = new RegionDao();
	String name = "";//字段
	String values = "";//值
	for (String[] strings : lidtr) {
		if (strings.length>0&&strings[0].length()>0&&strings[1].length()>0) {
				name += strings[0]+",";
				values += "'"+strings[1]+"',";		
		}
	}
	name = name.substring(0, name.length()-1);
	values = values.substring(0, values.length()-1);
	String sql = "INSERT INTO Region("+name+") VALUES ("+values+")";
	
	regiondao.addSql(sql);//添加用户
	// userDao.deleteById(1);// 删除Id为1的用户
	// userDao.update(new User(2, "李四", "1234546"));// 更新Id为2的用户信息
	// System.out.println(userDao.loadById(2)); // 查询Id为2的用户信息
	// 列出所有的用户
//	List<Region> list = regiondao.listRegion();
//	for (Region u : list) {
//	System.out.println(u.regionCode);
//	}
	
}
public static void updateRegionSql(String lidtr) {	
	RegionDao regiondao = new RegionDao();
	String sql = "update Region set "+lidtr;
	
	regiondao.addSql(sql);//添加用户
	// userDao.deleteById(1);// 删除Id为1的用户
	// userDao.update(new User(2, "李四", "1234546"));// 更新Id为2的用户信息
	// System.out.println(userDao.loadById(2)); // 查询Id为2的用户信息
	// 列出所有的用户
	List<Region> list = regiondao.listRegion();
	for (Region u : list) {
		System.out.println(u.regionCode);
	}
	
}
/**
 * 测试区域代码数据
 */
public static void ceshiRegion() {
	Region region = new Region();
	region.setRegionCode("123456");//区域代码
	region.setParentRegionCode("123456");//区域父代码
	region.setRetionName("123456");//区域名
	region.setHasSubRegion("123456");//级别
	region.setLevel("123456");
	RegionDao regiondao = new RegionDao();
	regiondao.add(region);//添加用户
	// userDao.deleteById(1);// 删除Id为1的用户
	// userDao.update(new User(2, "李四", "1234546"));// 更新Id为2的用户信息
	// System.out.println(userDao.loadById(2)); // 查询Id为2的用户信息
	// 列出所有的用户
	List<Region> list = regiondao.listRegion();
	for (Region u : list) {
	System.out.println(u);
	}
}
/**
 * 测试用户
 */
public static void ceshiUser() {
	User user = new User();
	user.setUsername("张三三");
	user.setPassword("123");
	UserDao userDao = new UserDao();
	 userDao.add(user);//添加用户
	// userDao.deleteById(1);// 删除Id为1的用户
	// userDao.update(new User(2, "李四", "1234546"));// 更新Id为2的用户信息
	// System.out.println(userDao.loadById(2)); // 查询Id为2的用户信息
	// 列出所有的用户
	List<User> list = userDao.listUser();
	for (User u : list) {
	System.out.println(u);
	}
}
}
