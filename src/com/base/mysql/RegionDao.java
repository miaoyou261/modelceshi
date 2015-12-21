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
public class RegionDao {
	// 添加用户
	public void add(Region u) {
		Connection conn = DBUtil.createConn();
		String sql = "insert into Region values(?,?,?,?,?)";
		PreparedStatement ps = DBUtil.prepare(conn, sql);
		try {
			ps.setString(1, u.getRegionCode());
			ps.setString(2, u.getParentRegionCode());
			ps.setString(3, u.getRetionName());
			ps.setString(4, u.getHasSubRegion());
			ps.setString(5, u.getLevel());
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
		String sql = "delete from Region where region_code=?";
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
	public void delete(Region u) {
		deleteById(u.getRegionCode());
	}

	// 更新用户
	public void update(Region u) {
		Connection conn = DBUtil.createConn();
		String sql = "update Region set region_code=?"
				+ ",parent_region_code=?"
				+ ",retion_name=?"
				+ ",has_sub_region=?"
				+ ",level=?"
				+ " where region_code=?";
		PreparedStatement ps = DBUtil.prepare(conn, sql);
		try {
			ps.setString(1, u.getRegionCode());
			ps.setString(2, u.getParentRegionCode());
			ps.setString(3, u.getRetionName());
			ps.setString(4, u.getHasSubRegion());
			ps.setString(5, u.getLevel());
			ps.setString(5, u.getLevel());
			ps.setString(6, u.getRegionCode());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.close(ps);
		DBUtil.close(conn);
	}

	// 根据查询用户
	public Region loadById(String id) {
		Connection conn = DBUtil.createConn();
		String sql = "select * from Region where region_code=?";
		PreparedStatement ps = DBUtil.prepare(conn, sql);
		Region region = null;
		ResultSet rs = null;
		try {
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				region = new Region();
				region.setRegionCode(rs.getString("region_code"));
				region.setParentRegionCode(rs.getString("parent_region_code"));
				region.setRetionName(rs.getString("retion_name"));
				region.setHasSubRegion(rs.getString("has_sub_region"));
				region.setLevel(rs.getString("level"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.close(rs);
		DBUtil.close(ps);
		DBUtil.close(conn);
		return region;
	}

	// 查询所有用户信息
	public  List<Region> listRegion() {
		Connection conn = DBUtil.createConn();
		String sql = "select * from Region";
		PreparedStatement ps = DBUtil.prepare(conn, sql);
		List<Region> list = new ArrayList<Region>();
		ResultSet rs = null;
		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				Region region = new Region();
				region.setRegionCode(rs.getString("region_code"));
				region.setParentRegionCode(rs.getString("parent_region_code"));
				region.setRetionName(rs.getString("retion_name"));
				region.setHasSubRegion(rs.getString("has_sub_region"));
				region.setLevel(rs.getString("level"));
				list.add(region);
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