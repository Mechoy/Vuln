package com.mechoy;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/4/5
 */
public class Person {
    public String name;
    public Integer age;

    public Person() {
        System.out.println("com.mechoy.Person >>> 无参构造器调用...");
    }

    public Person(String name) {
        System.out.println("com.mechoy.Person >>> 1个参数的有参构造器调用...");
        this.name = name;
    }

    public Person(String name, Integer age) {
        System.out.println("com.mechoy.Person >>> 2个参数的有参构造器调用...");
        this.name = name;
        this.age = age;
    }

    public String getName() {
        System.out.println("com.mechoy.Person >>> getName调用...");
        return name;
    }

    public void setName(String name) {
        System.out.println("com.mechoy.Person >>> setName调用...");
        this.name = name;
    }

    public Integer getAge() {
        System.out.println("com.mechoy.Person >>> getAge调用...");
        return age;
    }

    public void setAge(Integer age) {
        System.out.println("com.mechoy.Person >>> setAge调用...");
        this.age = age;
    }
}
