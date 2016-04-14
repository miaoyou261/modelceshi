package com.base.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.base.util.LinksDate0;
import com.base.util.WriteText;

/**
 * @author lhy
 * @description 对用户的CRUD相关操作
 */
public class RegionDao {
	// 添加用户
			public void addSql(String sql) {
				Connection conn = DBUtil.createConn();
//				String sql = "insert into Hospital values(?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps = DBUtil.prepare(conn, sql);
				try {
//					ps.setString(1, u.getId());
//					ps.setString(2, u.getHospitalName());
//					ps.setString(3, u.getRegionCode());
//					ps.setString(4, u.getAddress());
//					ps.setString(5, u.getHospitalTel());
//					ps.setString(6, u.getHospitalGrade());
//					ps.setString(7, u.getKeyDepartments());
//					ps.setString(8, u.getModeOfOperation());
//					ps.setString(9, u.geteMail());
//					ps.setString(10, u.getHospitalWebsite());
					ps.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				DBUtil.close(ps);
				DBUtil.close(conn);
			}
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

	// 根据地址查询
	public Region loadByRetionName(String id,String parentId) {
		Connection conn = DBUtil.createConn();
		String sql = "select * from Region where retion_name like ?";
		PreparedStatement ps = DBUtil.prepare(conn, sql);
		Region region = null;
		ResultSet rs = null;
		try {

			ps.setString(1, id+"%");
			rs = ps.executeQuery();
			
				while (rs.next()) {
					String fu = rs.getString("parent_region_code");
//					String fufu ="";
					
					Region regionfu = loadById(fu);
					if (regionfu.getParentRegionCode()!=null) {
						 fu = regionfu.getRetionName();
//						 Region regionfufu = loadById(regionfu.getParentRegionCode());
//						 if (regionfufu!=null) {
//							 fufu = regionfufu.getRetionName();							
//						}
//						 if(fufu==null)fufu="";
						 
					}
					
					
					region = new Region();
					region.setRegionCode(rs.getString("region_code"));
					region.setParentRegionCode(rs.getString("parent_region_code"));
					region.setRetionName(rs.getString("retion_name"));
					region.setHasSubRegion(rs.getString("has_sub_region"));
					region.setLevel(rs.getString("level"));
					//如果父类相同就跳出循环
					if (parentId.contains(fu)) {
						break;
					}
//					if (rs.getRow()>1&&parentId!=null) {
//						Region str =  loadByRetionName(parentId,null);
//						if(str.getRegionCode().equals(rs.getString("parent_region_code"))){
//							System.out.println(str.getRegionCode());
//							break;
//						}else{
////							WriteText.appendWrite("\n" +LinksDate0.time()+"查找父区域异常"+ parentId, "c:\\errorsave.txt");
//							System.out.println("记录这个数据是否有问题"+parentId);
//						}
//					}
					
				}

				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.close(rs);
		DBUtil.close(ps);
		DBUtil.close(conn);
		return region;
	}
	
	// 根据ID查询
		public Region loadById(String id) {
			Connection conn = DBUtil.createConn();
			String sql = "select * from Region where region_code=?";
			PreparedStatement ps = DBUtil.prepare(conn, sql);
			Region region = null;
			ResultSet rs = null;
			try {

				ps.setString(1, id); 
				rs = ps.executeQuery();
				
					while (rs.next()) {
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
		// 查询区域名信息
		public  List<Region> selectRegion(String name ,String value) {
			Connection conn = DBUtil.createConn();
			String sql = "select * from Region where "+name+" IN ("+value+")";
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
		/**
		 * 查询数据
		 * @param id 字段
		 * @param value 索引值
		 * @return
		 */
		public List<Region> selectSQL(String id,String expression, String... value) {
			Connection conn = DBUtil.createConn();
			String sql = "select * from Region where "+id+" "+expression+"?";
			for (int i = 1; value.length>i; i++) {
				sql += " "+value[i];
			}
			PreparedStatement ps = DBUtil.prepare(conn, sql);
			List<Region> list = new ArrayList();
//			Hospital hospital = null;
			ResultSet rs = null;
			try {
				ps.setString(1, value[0]);
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