package com.mechoy;

import java.util.Properties;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/5/8
 * Description
 */
public class Person {
    public Properties pro;

    public Properties getPro() {
        return pro;
    }

    public void setProperties(Properties pro) {
        System.out.println(pro.getProperty("zzz"));
        System.out.println(pro.getProperty("xxx"));
        this.pro = pro;
    }
}
