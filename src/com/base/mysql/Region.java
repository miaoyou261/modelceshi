package com.base.mysql;


public class Region {

	public String regionCode;
	public String parentRegionCode;
	public String retionName;
	public String hasSubRegion;
	public String level;
	
	public Region() {
	}
	public Region(String regionCode, String parentRegionCode, String retionName,String hasSubRegion,String level) {

	this.regionCode = regionCode;
	this.parentRegionCode = parentRegionCode;
	this.retionName = retionName;
	this.hasSubRegion = hasSubRegion;
	this.level = level;
	}
	@Override
	public String toString() {
	return "User [region_code=" + regionCode 
			+ ", parent_region_code=" + parentRegionCode 
			+ ", retion_name=" + retionName 
			+ ", has_sub_region=" + hasSubRegion 
			+ ", level="+ level + "]";
	}
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
	  
	  
}
