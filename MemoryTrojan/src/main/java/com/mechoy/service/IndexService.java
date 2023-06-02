package com.mechoy.service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/5/10
 * Description
 */
public interface IndexService {
    /**
     * CC反序列化插入 内存马
     * @param poc
     * @return
     */
    public boolean pocExec(String poc) throws IOException;
}
