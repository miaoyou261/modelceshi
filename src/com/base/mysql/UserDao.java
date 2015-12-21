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
public class UserDao {
	// 添加用户
	public void add(User u) {
		Connection conn = DBUtil.createConn();
		String sql = "insert into user values(null,?,?)";
		PreparedStatement ps = DBUtil.prepare(conn, sql);
		try {
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.close(ps);
		DBUtil.close(conn);
	}

	// 根据Id删除用户
	public void deleteById(int id) {
		Connection conn = DBUtil.createConn();
		String sql = "delete from user where id=?";
		PreparedStatement ps = DBUtil.prepare(conn, sql);
		try {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.close(ps);
		DBUtil.close(conn);
	}

	// 删除用户
	public void delete(User u) {
		deleteById(u.getId());
	}

	// 更新用户
	public void update(User u) {
		Connection conn = DBUtil.createConn();
		String sql = "update user set username=?,password=? where id=?";
		PreparedStatement ps = DBUtil.prepare(conn, sql);
		try {
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setInt(3, u.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.close(ps);
		DBUtil.close(conn);
	}

	// 根据查询用户
	public User loadById(int id) {
		Connection conn = DBUtil.createConn();
		String sql = "select * from user where id=?";
		PreparedStatement ps = DBUtil.prepare(conn, sql);
		User user = null;
		ResultSet rs = null;
		try {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("Id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.close(rs);
		DBUtil.close(ps);
		DBUtil.close(conn);
		return user;
	}

	// 查询所有用户信息
	public List<User> listUser() {
		Connection conn = DBUtil.createConn();
		String sql = "select * from user";
		PreparedStatement ps = DBUtil.prepare(conn, sql);
		List<User> list = new ArrayList<User>();
		ResultSet rs = null;
		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("Id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				list.add(user);
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