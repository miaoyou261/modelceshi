package com.base.advanced.dynamicCompile;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * 动态编译
 * 
 * @author Administrator
 *
 */
public class DynamicCompile {
	 /**
     * @param args
     * @throws Exception 
     * ￥-824.40 -824.40 
     */
    public static void main(String[] args)  throws Exception { 
    	DynamicCompile dynamiccompile = new DynamicCompile();
//    	dynamiccompile.compilefile();//直接编译现有的java文件
    	
    	dynamiccompile.compileString("input_str");
}
    /**
     * 编译字符串 方法不带参数
     */
    public void compileString(String str) {

        System.out.println(System.getProperty("user.dir"));//当前工作目录
//        String s = "public class Temp{" ;
//        s+="\r\n"+"      public static String call(){      ";        
//        s+="\r\n"+"            System.out.println(\""+str+"\");  ";
//        s+="\r\n"+"            return \"return_str\"; ";
//        s+="\r\n"+"      }";
//        s+="\r\n"+"}";
        String s = "public class Temp {"
        		+ "public void call(){"
        		+ "System.out.println(\"Hello word!\");"
        				+ "}"
        				+ "}";
        try{
            File file = new File(System.getProperty("user.dir")+"/src/com/base/advanced/dynamicCompile/Temp.java");
            PrintWriter pw = new PrintWriter(new FileWriter(file));
            //pw.println(s);
            pw.write(s);
            pw.close();
            
            //动态编译
            JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
            int status = javac.run(null, null, null, "-d",System.getProperty("user.dir")+"/bin/com/base/advanced/dynamicCompile","/src/com/base/advanced/dynamicCompile/Temp.java");
            if(status!=0){
                System.out.println("没有编译成功！");
            }

            execution("file:/D:/workspace/modelceshi/bin/com/base/advanced/dynamicCompile/","Temp","call");
            //动态执行
//        Class cls = Class.forName("com.base.advanced.dynamicCompile.Temp");//返回与带有给定字符串名的类 或接口相关联的 Class 对象。
//        Method method = cls.getDeclaredMethod("call", String.class);//返回一个 Method 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明方法
//        String result= (String)method.invoke(null, str);//静态方法第一个参数可为null,第二个参数为实际传参
//        System.out.println(result);
        }catch(Exception e){
            e.printStackTrace();
        }
	}
    /**
     * 直接编译现有的java文件
     */
    public void compilefile() {
    	try {
    	// 编译程序  
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();  
        //这里容易出现一个空指针报错 的问题
        //解决办法C:\Program Files (x86)\Java\jdk1.8.0_25\lib\tools.jar 拷贝到 C:\Program Files (x86)\Java\jre1.8.0_25\lib\tools.jar
        //原因是运行的C:\Program Files (x86)\Java\jre1.8.0_25\bin中的javac.exe文件进行编译的
        int result = javaCompiler.run(null, null, null,"./src/com/base/advanced/dynamicCompile/Hello.java");  
        System.out.println( result == 0 ? "恭喜编译成功" : "对不起编译失败");  
        execution("file:/D:/workspace/modelceshi/src/com/base/advanced/dynamicCompile/","Hello","printString");
//        URL urls[] = new URL[]{ new URL("file:/D:/workspace/modelceshi/src/com/base/advanced/dynamicCompile/")}; //储存文件目录的地址
//        URLClassLoader uLoad = new URLClassLoader(urls);  //classloader从哪个目录找？ 
//        //uLoad.loadClass("Hello");
//        Class c = uLoad.loadClass("Hello");  //找哪个class文件 注意不带后缀名  
//        //c.newInstance();  //创建一个实例  
//         
//        Method method = c.getMethod("printString");
//        
//        	method.invoke(c.newInstance());
//            String string = (String) method.invoke(c.newInstance());//这里toString会报错
//            System.out.println(string);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
    
    /**
     * 
     */
    public void execution(String uri,String name,String MethodName) {
    	try{
    	URL urls[] = new URL[]{ new URL(uri)}; //储存文件目录的地址
        URLClassLoader uLoad = new URLClassLoader(urls);  //classloader从哪个目录找？ 
        //uLoad.loadClass("Hello");
        Class c = uLoad.loadClass(name);  //找哪个class文件 注意不带后缀名  
        //c.newInstance();  //创建一个实例  
         
        Method method = c.getMethod(MethodName);
        
        	method.invoke(c.newInstance());
    } catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	}
    
}
