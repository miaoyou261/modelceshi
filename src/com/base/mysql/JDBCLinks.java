package com.base.mysql;

import java.sql.*;


public class JDBCLinks {
	public static void main(String[] args) {
		createRegion();
		createHospital();
	}
	
	/**
	 * 加载驱动连接数据库
	 * @return
	 */
	private static Connection getConn() {
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/playdb";
	    String username = "root";
	    String password = "123456";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	//插入数据
	private static int SetSql(String sql) {
	    Connection conn = getConn();
	    int i = 0;
//	    sql = "insert into students (Name,Sex,Age) values(?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	private static int Setsql(String sql) {
		Connection conn = getConn();
		int i = 0;
//	    sql = "insert into students (Name,Sex,Age) values(?,?,?)";
		Statement pstmt;
		try {
			pstmt = conn.createStatement();
			pstmt.executeUpdate(sql);
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public void insertDate(String sql) {
		sql = "INSERT INTO hospital(id,hospital_name,region_code,address,hospital_tel,hospital_grade,key_departments,mode_of_operation,e_mail,hospital_website)"
				+ "VALUES (id,hospital_name,region_code,address,hospital_tel,hospital_grade,key_departments,mode_of_operation,e_mail,hospital_website)";
		
	}
	/**
	 * 创建医院表
	 * @return
	 */
	private static int createHospital() {
		String sql ="CREATE TABLE hospital ("
				+"`id` VARCHAR(40) NOT NULL,"
 				+"`hospital_name` VARCHAR(255) DEFAULT NULL,"  
				+" `region_code` VARCHAR(255) DEFAULT NULL,"
 				+ "`address` VARCHAR(255) DEFAULT NULL," 
 				+"`hospital_tel` VARCHAR(255) DEFAULT NULL,"
 				+"`hospital_grade` VARCHAR(255) DEFAULT NULL,"
 				+"`key_departments` VARCHAR(255) DEFAULT NULL,"
 				+" `mode_of_operation` VARCHAR(255) DEFAULT NULL,"
 				+" `mode_of_operation` VARCHAR(255) DEFAULT NULL,"
 				+" `e_mail` VARCHAR(255) DEFAULT NULL,"
 				+"`hospital_website` VARCHAR(255) DEFAULT NULL,"
 				+" PRIMARY KEY (`id`)"
 				+ ") ENGINE=INNODB DEFAULT CHARSET=utf8";
		
		return Setsql(sql);
		
	}
	/**
	 * 创建城市表
	 * @return
	 */
	private static int createRegion() {
		String sql ="CREATE TABLE region ("
				+ "`region_code` varchar(255) NOT NULL,"
				+ "`parent_region_code` varchar(255) DEFAULT NULL,"
				+ "`retion_name` varchar(255) DEFAULT NULL,"
				+ "`has_sub_region` varchar(255) DEFAULT NULL,"
				+ "`level` varchar(255) DEFAULT NULL,"
				+ "PRIMARY KEY (`region_code`)"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8"; 		
		return Setsql(sql);
		
	}
}
