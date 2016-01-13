package com.base.model;
import java.util.Date;

public class PatientProgram {
	public java.util.UUID id;
	
	public String phoneNumber;//患者手机号

	public String PersonalProgramCode; //个人方案代码
	
	public String programCode; //方案代码
	
	public String surveyCode;//问卷代码
	
	public String anIntroduction;//方案介绍
	
	public int statu;//状态
	
	public Date ComputeTime;//评估时间
	
	public Date finishTime;//完成时间
	
}
