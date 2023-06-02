package com.mechoy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/4/5
 */
public class fastJsonTest {
    public static void main(String[] args) {

        // JavaBean 转Json字符串
//        Person mechoy = new Person("Mechoy", 56);
//        String s = JSON.toJSONString(mechoy);
//        System.out.println(s);

        // Json字符串转JavaBean
//        String str1 = "{\"name\":\"zzz\",\"age\":15}";
        String str2 = "{\"@type\":\"com.mechoy.Person\",\"aaa\":\"bbb\",\"name\":\"xxx\",\"age\":16}";
//        System.out.println("-----------parse测试-----------");
//        Object parse = JSON.parse(str1);
//        System.out.println("-----------parseObject测试-----------");
//        Object parse1 = JSON.parseObject(str1, Person.class);
//        System.out.println("-----------@type parse测试-----------");
//        Object parse2 = JSON.parse(str2);
//        System.out.println("-----------@type parseObject测试-----------");
        Object parse = JSON.parseObject(str2, Feature.SupportNonPublicField);

//        String cmd = "{\"@type\":\"com.mechoy.Execute\",\"cmd\":\"calc\"}";
//        Object parse = JSON.parse(cmd);
//        JSONObject jsonObject = JSON.parseObject(cmd);
        // JavaBean转Json
//        System.out.println("JavaBean转Json");
//        Person mechoy = new Person("Mechoy", 33);
//        Object o = JSON.toJSON(mechoy);
    }
}
