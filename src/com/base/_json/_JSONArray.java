package com.base._json;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.base.MyReflect._Class;
import com.base.model.PatientProgram;


public class _JSONArray {
	public static Map<String,Object> programMap = new HashMap<String,Object>();
public static void main(String[] args) {
	String json = "{\"ComputeTime\":1442073600000,\"PersonalProgramCode\":null,\"anIntroduction\":\"说明预付费1\",\"finishTime\":null,\"id\":\"7ec3c887-6dee-4b2f-8088-17f9368af170\",\"phoneNumber\":\"60eda729-2290-4db4-9b8e-5089c6261421\",\"programCode\":\"Program1\",\"surveyCode\":\"survey01\",\"statu\":0,\"computeTime\":1442073600000,\"personalProgramCode\":null}";
	jsonToModel(json);
	PatientProgram pro = new PatientProgram();
	new _Class().jsontobean(pro,programMap);
	System.out.println(pro);
//	int i = programMap.size();
//	String[] strs = new String[i];
//	for (String string :  programMap.keySet()) {
//		String st = (String) programMap.get(string);
//		if (st.isEmpty()) {
//			System.out.println(programMap.get(string));			
//		}
//	}
}

/**
 * 处理JSON数据
 * @param json
 */
public static void jsonToModel(String json) {
	json = cutString(json,"{","}");
	String[] strjson = json.split(",");
	for (String string : strjson) {
		String[] str = string.split(":");
		if (str.length>1) {
			str[0] = cutString(str[0],"\"","\"");
			if (str[1].contains("\"")) {
				str[1] = cutString(str[1],"\"","\"");
			}
			if(!string.contains("null")){
				programMap.put(str[0],str[1]);
			}else{
				programMap.put(str[0],"");
			}
		}
	}
}
/**
 * 
 * @param json 数据
 * @param up 去掉up字符前的数据
 * @param later 去掉later字符后的数据
 * @return
 */
public static  String cutString(String json,String up,String later) {
	json=json.substring(json.indexOf(json)+1, json.length());
	json=json.substring(0, json.indexOf(later));
	return json;
	
}
}
