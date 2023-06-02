package com.mechoy;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/3/27
 */
public class LoadTest {
    public static void main(String[] args) throws Exception {
//        Class<?> aClass = Class.forName("com.mechoy.Car");
//        Object o = aClass.newInstance();
//        System.out.println(aClass.getClassLoader());


//        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new URL("file:///C:\\Users\\53433\\Desktop\\class\\")});
//        Class<?> cattle = urlClassLoader.loadClass("Cattle");
//        cattle.newInstance();
        FileInputStream fis = new FileInputStream("C:\\Users\\53433\\Desktop\\class\\Cattle.class");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        byte[] classBytes = baos.toByteArray();

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        Method defineClass = ClassLoader.class.getDeclaredMethod("defineClass", byte[].class, int.class, int.class);
        defineClass.setAccessible(true);
        Class<?> cattle = (Class<?>) defineClass.invoke(systemClassLoader, classBytes, 0, classBytes.length);
        cattle.newInstance();


        String sql = "aaasssdddfffggghhhjjjkkk";
        int sss = sql.indexOf("aaa");
        String substring = sql.substring(0 + "aaa".length());
        System.out.println(substring + " >>> "+substring.length());
        System.out.println(sss);



    }
}
