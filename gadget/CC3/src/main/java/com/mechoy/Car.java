package com.mechoy;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/3/27
 */
public class Car {
    static {
        System.out.println("静态代码块加载");
    }

    public String name;
    public Integer age;

    public Car() {
    }

    public Car(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
