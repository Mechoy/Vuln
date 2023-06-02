package com.mechoy;

import java.io.IOException;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/4/16
 * Description
 */
public class Execute {
    public String cmd;

    public Execute() {
    }

    public Execute(String cmd) {
        this.cmd = cmd;
    }

    private String getCmd() {
        return cmd;
    }

    private void setCmd(String cmd) throws IOException {
        this.cmd = cmd;
        Runtime.getRuntime().exec(this.cmd);
    }
}
