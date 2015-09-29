package com.base.advanced.dynamicCompile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
 
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
 
 
/**
 * 测试动态编译的基本原理
 * @author 尚学堂高淇 www.sxt.cn
 *
 */
public class Demo01 {
     
    public static int compileFile(String sourceFile){
        //动态编译
                JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
                int result = compiler.run(null, null, null,sourceFile);
//              int result = compiler.run(null, null, null,"d:/myjava/HelloWorld.java");
//              int result = compiler.run(null, null, null,"./temp/HelloWorld.java");
                System.out.println(result==0?"编译成功":"编译失败");
                return result;
    }
     
    //通过Runtime.getRuntime().exec()运行程序
    public static void runJavaClass(String dir,String classFile) throws Exception{
            //动态运行
                Runtime run = Runtime.getRuntime();  
//              Process process = run.exec("java -cp d:/myjava  HelloWorld");  
                Process process = run.exec("java -cp "+dir+" "+classFile);  
                 
                InputStream in = process.getInputStream();  
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));  
                String info  = "";  
                while ((info = reader.readLine()) != null) {  
                    System.out.println(info);  
                }
    }
    //通过反射运行类的main方法
    public static void runJavaClassByReflect(String dir,String classFile) throws Exception{
         try {
              URL[] urls = new URL[] {new URL("file:/"+dir)};
              URLClassLoader loader = new URLClassLoader(urls);
              Class c = loader.loadClass(classFile);
              //调用加载类的main方法
              c.getMethod("main",String[].class).invoke(null, (Object)new String[]{});
             //由于可变参数是JDK5.0之后才有，为了兼容直接写数组会直接转成多个参数。
             //m.invoke(null, (Object)new String[]{});会编译成:m.invoke(null,"aa","bb"),就发生了参数个数不匹配的问题。
             //因此，必须要加上(Object)转型，避免这个问题。
             //public static void main(String[] args)
 
 
 } catch (Exception e) {
              e.printStackTrace();
          }
    }
     
     
    public static void main(String[] args) throws Exception {
        compileFile("D:/workspace/modelceshi/src/com/base/advanced/dynamicCompile/Hello.java");
//      runJavaClass("d:/myjava", "HelloWorld");
        runJavaClassByReflect("D:/workspace/modelceshi/src/com/base/advanced/dynamicCompile/", "Hello");
    }
}