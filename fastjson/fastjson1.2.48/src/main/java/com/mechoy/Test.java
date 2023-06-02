package com.mechoy;

import java.io.IOException;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/5/6
 * Description
 */
public class Test implements AutoCloseable{

    public String cmd;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) throws IOException {
        Runtime.getRuntime().exec(cmd);
        this.cmd = cmd;
    }

    @Override
    public void close() throws Exception {

    }
}
