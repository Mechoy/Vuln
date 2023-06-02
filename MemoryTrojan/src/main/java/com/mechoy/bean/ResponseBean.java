package com.mechoy.bean;
/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/5/11
 * Description
 */
public class ResponseBean {
    public String code;
    public String message;

    public ResponseBean() {
    }

    public ResponseBean(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
