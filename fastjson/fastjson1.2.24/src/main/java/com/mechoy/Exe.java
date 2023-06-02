package com.mechoy;

import java.io.IOException;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/4/18
 * Description
 */
public class Exe {
    static {
        try {
            Runtime.getRuntime().exec("calc");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
