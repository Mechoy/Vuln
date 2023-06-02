package com.mechoy;

import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.map.LazyMap;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/4/2
 */
public class Test {
    public static String[] str = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
            "q", "r", "s", "t", "u", "v", "w", "x", "y", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public static void main(String[] args) {
        indexEquals();
//        HashMap hashMap = new HashMap();
//        hashMap.put("aa",1);
//        System.out.println(hashMap.hashCode());
//        System.out.println(hashMap.hashCode());
    }


    /**
     * 查找不同key,却相同的hashCode
     */
    public static void indexEquals() {
        HashMap<Object, Object> map1 = new HashMap<>();
        HashMap<Object, Object> map2 = new HashMap<>();
        for (Integer m = 0; m < str.length; m++) {
            for (Integer n = 0; n < str.length; n++) {
                String baseKey = str[m] + str[n];
                map1.put(baseKey, 1);
                for (Integer i = 0; i < str.length; i++) {
                    for (Integer j = 0; j < str.length; j++) {
                        String s = str[i] + str[j];
                        map2.put(s, 1);
                        if (map2.hashCode() == map1.hashCode() && !baseKey.equals(s)) {
                            // 此处再计算hashCode时，会比正常的小一
                            System.out.println(baseKey.hashCode());
                            System.out.println(baseKey+ " ==== " + s + " >>> hashCode相等");
                        }
                        map2.remove(s);
                    }
                }
                map1.remove(baseKey);
            }
        }
    }
}
