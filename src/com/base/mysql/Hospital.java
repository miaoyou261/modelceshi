package com.base.mysql;

import java.util.UUID;


public class Hospital { 

	public String id;
	
	public String HospitalName;//医院名称
	
	public String regionCode;//区域代码
	
	public String address;//医院地址
	
	public String HospitalTel;//医院电话
	
	public String HospitalGrade;//医院等级
	
	public String  keyDepartments;//重点科室	

	public String ModeOfOperation;//经营方式
	
	public String eMail;//电子邮箱
	
	public String hospitalWebsite;//医院网站
	public Hospital() {
	}
	public Hospital(String id ,String HospitalName, String regionCode, String address,String HospitalTel,
			String HospitalGrade,String keyDepartments,String ModeOfOperation,String eMail,String hospitalWebsite) {

	this.id = id;
	this.HospitalName = HospitalName;
	this.regionCode = regionCode;
	this.address = address;
	this.HospitalTel = HospitalTel;
	this.HospitalGrade = HospitalGrade;
	this.keyDepartments = keyDepartments;
	this.ModeOfOperation = ModeOfOperation;
	this.eMail = eMail;
	this.hospitalWebsite = hospitalWebsite;
	}
	@Override
	public String toString() {
	return "User [id=" + id 
			+ ", HospitalName=" + HospitalName 
			+ ", regionCode=" + regionCode 
			+ ", address=" + address 
			+ ", HospitalTel=" + HospitalTel 
			+ ", HospitalGrade=" + HospitalGrade 
			+ ", keyDepartments=" + keyDepartments 
			+ ", ModeOfOperation=" + ModeOfOperation 
			+ ", eMail=" + eMail 
			+ ", hospitalWebsite=" + hospitalWebsite 
			+ "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHospitalName() {
		return HospitalName;
	}

	public void setHospitalName(String hospitalName) {
		HospitalName = hospitalName;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHospitalTel() {
		return HospitalTel;
	}

	public void setHospitalTel(String hospitalTel) {
		HospitalTel = hospitalTel;
	}

	public String getHospitalGrade() {
		return HospitalGrade;
	}

	public void setHospitalGrade(String hospitalGrade) {
		HospitalGrade = hospitalGrade;
	}

	public String getKeyDepartments() {
		return keyDepartments;
	}

	public void setKeyDepartments(String keyDepartments) {
		this.keyDepartments = keyDepartments;
	}

	public String getModeOfOperation() {
		return ModeOfOperation;
	}

	public void setModeOfOperation(String modeOfOperation) {
		ModeOfOperation = modeOfOperation;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getHospitalWebsite() {
		return hospitalWebsite;
	}

	public void setHospitalWebsite(String hospitalWebsite) {
		this.hospitalWebsite = hospitalWebsite;
	}

	

}
