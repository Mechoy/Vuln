package com.mechoy;

import sun.reflect.annotation.AnnotationType;

import java.lang.annotation.Target;
import java.util.Map;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/3/24
 */
public class Test {

    public static void main(String[] args) {
        AnnotationType instance = AnnotationType.getInstance(Target.class);
        System.out.println(instance);
        Map<String, Class<?>> stringClassMap = instance.memberTypes();
        System.out.println("------------^^^^^-----------");
        System.out.println(stringClassMap);
        Class<?> value = stringClassMap.get("value");
        System.out.println(value);
    }
}
