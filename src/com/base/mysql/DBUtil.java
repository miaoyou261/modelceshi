package com.base.mysql;

import java.sql.*;

/**
 * @author lhy
 * @description 数据库工具类
 */
public class DBUtil {
	// 创建连接
	public static Connection createConn() {
		Connection conn = null;
		try {
			// 加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			// 获取连接(这里用户名为root，密码为空)
			conn = DriverManager
					.getConnection(
							"jdbc:mysql://localhost/playdb?useUnicode=true&characterEncoding=utf-8",
							"root", "123456");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 创建回话,获取预处理语句（可以防止sql语句注入）
	public static PreparedStatement prepare(Connection conn, String sql) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}

	// 关闭连接
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 关闭回话
	public static void close(Statement st) {
		if (st != null) {
			try {
				st.close();
				st = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 关闭查询结果集
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
