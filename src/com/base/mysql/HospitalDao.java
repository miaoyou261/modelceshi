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
		String sql = "update user set id=?"
				+ ",HospitalName=?"
				+ ",regionCode=?"
				+ ",address=?"
				+ ",HospitalTel=?"
				+ ",HospitalGrade=?"
				+ ",keyDepartments=?"
				+ ",ModeOfOperation=?"
				+ ",eMail=?"
				+ ",hospitalWebsite=?"
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
			ps.setString(11, u.getId().toString());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.close(ps);
		DBUtil.close(conn);
	}

	// 根据查询用户
	public Hospital loadById(String id) {
		Connection conn = DBUtil.createConn();
		String sql = "select * from Hospital where id=?";
		PreparedStatement ps = DBUtil.prepare(conn, sql);
		Hospital hospital = null;
		ResultSet rs = null;
		try {
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				hospital = new Hospital();
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.close(rs);
		DBUtil.close(ps);
		DBUtil.close(conn);
		return hospital;
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