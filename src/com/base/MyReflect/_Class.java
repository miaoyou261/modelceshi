package com.base.MyReflect;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
public class _Class {

public static void main(String[] args) {
	
		Date date = new Date();
		Long time = date.getTime();
		System.out.println(time);

		Date d = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		System.out.println(sdf.format(d));
	
}
public static void ceshi() {
	try {
		Class s = Class.forName("com.base.model.PatientProgram");
		Field[] flds = s.getFields();
		for (Field field : flds) {
			System.out.println("对项名"+field.getName());
			System.out.println("对项类型"+field.getType());
			
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


/**
 * 转换对象
 * @param obj 类型对象
 * @param programMap json转换成的数据对
 * @param field 反射字段
 * @throws Exception
 */
public void changeType(Object obj,Map<String,Object> programMap,Field field) throws Exception {
	if(programMap.get(field.getName()).toString().length()==0){
		System.out.println("");
	}else
	if (field.getGenericType().toString().equals("class java.util.UUID")) {
		field.set(obj, java.util.UUID.fromString(programMap.get(field.getName()).toString())); 
	} else if (field.getGenericType().toString().equals("class java.lang.String")) {
		field.set(obj, (String)programMap.get(field.getName())); 
	} else if (field.getGenericType().toString().contains("java.util.Date")) {
		long time = Long.parseLong(programMap.get(field.getName()).toString());
		 Date d = new Date(time);
		 System.out.println(d);
		field.set(obj, d); 
	} else if (field.getGenericType().toString().equals("int")) {
		field.set(obj, Integer.parseInt(programMap.get(field.getName()).toString())); 
	} else if (field.getGenericType().toString().equals("class java.lang.Integer")) {
		field.set(obj, (Integer)programMap.get(field.getName())); 
	} else if (field.getGenericType().toString().equals("class java.lang.Double")) {
		field.set(obj, (Double)programMap.get(field.getName())); 
	} else if (field.getGenericType().toString().equals("class java.lang.Boolean")) {
		field.set(obj, (Boolean)programMap.get(field.getName())); 
	} else if (field.getGenericType().toString().equals("boolean")) {
		field.set(obj, (boolean)programMap.get(field.getName())); 
	} else if (field.getGenericType().toString().equals("class java.lang.Short")) {
		field.set(obj, (Short)programMap.get(field.getName())); 

	}
}
/**
 * 
 * @param obj 类型对象
 * @param programMap json转换成的数据对
 */
public void jsontobean(Object obj, Map<String,Object> programMap) {
	try {
		Class s = obj.getClass();
		
		Field[] flds = s.getFields();
		for (Field field : flds) {
			changeType(obj, programMap,field);
			
		}
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
/**
 * 
 * @param obj 类引用对象
 * @param propertyName 成员变量
 * @param value 要改变的值
 * @throws Exception
 */
public static void setProperty(Object obj, String propertyName, Object value) throws Exception  
{  
    Class<?> clazz = obj.getClass();  
    Field field = clazz.getField(propertyName);  
    field.set(obj, value);  
}

}
