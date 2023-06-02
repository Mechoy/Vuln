package com.mechoy;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;

import java.io.*;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/4/9
 */
public class Jdk7u21Test1 {
    final static String path = "ser.bin";

    public static void main(String[] args) {

    }

    public static void serialize(Object obj) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
    }

    public static Object deserialize() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object o = ois.readObject();
        return o;
    }

    public static byte[] classBytes() throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\53433\\Desktop\\class\\Cattle.class");
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
