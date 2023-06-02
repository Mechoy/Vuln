package com.mechoy;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/3/30
 */
public class Car {
    static {
        System.out.println("测试InstantiateTransformer...>>> 静态代码块被调用");
    }

    {
        System.out.println("测试InstantiateTransformer...>>> 构造代码块被调用");
    }

    public Car() {
        System.out.println("测试InstantiateTransformer...>>> 构造器被调用");
    }
}
