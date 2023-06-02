package com.mechoy.service.impl;

import com.mechoy.service.IndexService;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;
import java.util.Collection;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/5/10
 * Description
 */
public class IndexServiceImpl implements IndexService {
    @Override
    public boolean pocExec(String poc) throws IOException {
        byte[] bytes = Base64.getDecoder().decode(poc);
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        try {
            Object o = ois.readObject();
            bis.close();
            ois.close();
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            bis.close();
            ois.close();
            return false;
        }finally {
            return false;
        }
    }

}
