package com.base.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lhy
 * @description 对用户的CRUD相关操作
 */
public class HospitalDao {
	// 添加用户
		public void addSql(String sql) {
			Connection conn = DBUtil.createConn();
//			String sql = "insert into Hospital values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = DBUtil.prepare(conn, sql);
			try {
//				ps.setString(1, u.getId());
//				ps.setString(2, u.getHospitalName());
//				ps.setString(3, u.getRegionCode());
//				ps.setString(4, u.getAddress());
//				ps.setString(5, u.getHospitalTel());
//				ps.setString(6, u.getHospitalGrade());
//				ps.setString(7, u.getKeyDepartments());
//				ps.setString(8, u.getModeOfOperation());
//				ps.setString(9, u.geteMail());
//				ps.setString(10, u.getHospitalWebsite());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
	// 添加用户
	public void add(Hospital u) {
		Connection conn = DBUtil.createConn();
		String sql = "insert into Hospital values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = DBUtil.prepare(conn, sql);
		try {
			ps.setString(1, u.getId());
			ps.setString(2, u.getHospitalName());
			ps.setString(3, u.getRegionCode());
			ps.setString(4, u.getAddress());
			ps.setString(5, u.getHospitalTel());
			ps.setString(6, u.getHospitalGrade());
			ps.setString(7, u.getKeyDepartments());
			ps.setString(8, u.getModeOfOperation());
			ps.setString(9, u.geteMail());
			ps.setString(10, u.getHospitalWebsite());
			ps.setInt(11, u.getOrdernb());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.close(ps);
		DBUtil.close(conn);
	}

	// 根据区域码删除
	public void deleteById(String id) {
		Connection conn = DBUtil.createConn();
		String sql = "delete from Hospital where id=?";
		PreparedStatement ps = DBUtil.prepare(conn, sql);
		try {
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.close(ps);
		DBUtil.close(conn);
	}

	// 删除某条数据
	public void delete(Hospital u) {
		deleteById(u.getId().toString());
	}

	// 更新用户
	public void update(Hospital u) {
		Connection conn = DBUtil.createConn();
		String sql = "update Hospital set id=?"
				+ ",hospital_name=?"
				+ ",region_code=?"
				+ ",address=?"
				+ ",hospital_tel=?"
				+ ",hospital_grade=?"
				+ ",key_departments=?"
				+ ",mode_of_operation=?"
				+ ",e_mail=?"
				+ ",hospital_website=?"
				+ ",ordernb=?"
				+ " where id=?";
		PreparedStatement ps = DBUtil.prepare(conn, sql);
		try {
			ps.setString(1, u.getId());
			ps.setString(2, u.getHospitalName());
			ps.setString(3, u.getRegionCode());
			ps.setString(4, u.getAddress());
			ps.setString(5, u.getHospitalTel());
			ps.setString(6, u.getHospitalGrade());
			ps.setString(7, u.getKeyDepartments());
			ps.setString(8, u.getModeOfOperation());
			ps.setString(9, u.geteMail());
			ps.setString(10, u.getHospitalWebsite());
			ps.setInt(11, u.getOrdernb());
			ps.setString(12, u.getId().toString());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.close(ps);
		DBUtil.close(conn);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Hospital loadById(String id) {
		List<Hospital> list = selectSQL("id","=",id);
		if (list==null||list.size()==0) {
			return null;
		}
		return list.get(0);
	}
	/**
	 * 查询数据
	 * @param id 字段
	 * @param value 索引值
	 * @return
	 */
	public List<Hospital> selectSQL(String id,String expression, String... value) {
		Connection conn = DBUtil.createConn();
		String sql = "select * from Hospital where "+id+" "+expression+"?";
		for (int i = 1; value.length>i; i++) {
			sql += " "+value[i];
		}
		PreparedStatement ps = DBUtil.prepare(conn, sql);
		List<Hospital> hospitalList = new ArrayList();
//		Hospital hospital = null;
		ResultSet rs = null;
		try {
			ps.setString(1, value[0]);
		rs = ps.executeQuery();
		while (rs.next()) {
			Hospital hospital = new Hospital();
			hospital.setId(rs.getString("id"));
			hospital.setHospitalName(rs.getString("hospital_name"));
			hospital.setRegionCode(rs.getString("region_code"));
			hospital.setAddress(rs.getString("address"));
			hospital.setHospitalTel(rs.getString("hospital_tel"));
			hospital.setHospitalGrade(rs.getString("hospital_grade"));
			hospital.setKeyDepartments(rs.getString("key_departments"));
			hospital.setModeOfOperation(rs.getString("mode_of_operation"));
			hospital.seteMail(rs.getString("e_mail"));
			hospital.setHospitalWebsite(rs.getString("hospital_website"));
			hospital.setOrdernb(rs.getInt("ordernb"));
			hospitalList.add(hospital);
		}
	} catch (SQLException e) {
		System.out.println(ps.toString());
		e.printStackTrace();
	}
	DBUtil.close(rs);
	DBUtil.close(ps);
	DBUtil.close(conn);
	return hospitalList;
	}

	// 查询所有用户信息
	public  List<Hospital> listHospital() {
		Connection conn = DBUtil.createConn();
		String sql = "select * from Hospital";
		PreparedStatement ps = DBUtil.prepare(conn, sql);
		List<Hospital> list = new ArrayList<Hospital>();
		ResultSet rs = null;
		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				Hospital hospital = new Hospital();
				hospital.setId(rs.getString("id"));
				hospital.setHospitalName(rs.getString("hospital_name"));
				hospital.setRegionCode(rs.getString("region_code"));
				hospital.setAddress(rs.getString("address"));
				hospital.setHospitalTel(rs.getString("hospital_tel"));
				hospital.setHospitalGrade(rs.getString("hospital_grade"));
				hospital.setKeyDepartments(rs.getString("key_departments"));
				hospital.setModeOfOperation(rs.getString("mode_of_operation"));
				hospital.seteMail(rs.getString("e_mail"));
				hospital.setHospitalWebsite(rs.getString("hospital_website"));
				hospital.setOrdernb(rs.getInt("ordernb"));
			list.add(hospital);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.close(rs);
		DBUtil.close(ps);
		DBUtil.close(conn);
		return list;
	}
}