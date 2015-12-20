package com.base.mysql;


public class Region {
	
	public String regionCode;//区域代码
	public String parentRegionCode;//父级区域代码
	public String retionName;//区域名
	public String hasSubRegion;//深度级别
	
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public String getParentRegionCode() {
		return parentRegionCode;
	}
	public void setParentRegionCode(String parentRegionCode) {
		this.parentRegionCode = parentRegionCode;
	}
	public String getRetionName() {
		return retionName;
	}
	public void setRetionName(String retionName) {
		this.retionName = retionName;
	}
	public String getHasSubRegion() {
		return hasSubRegion;
	}
	public void setHasSubRegion(String hasSubRegion) {
		this.hasSubRegion = hasSubRegion;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String level;
}
