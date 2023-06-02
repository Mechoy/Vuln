package com.mechoy.bean;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/5/23
 * Description
 */
public class ServletBean {
    public String servletName;
    public String servletClass;
    public String servletMapping;

    public ServletBean() {
    }

    public ServletBean(String servletName, String servletClasse, String servletMapping) {
        this.servletName = servletName;
        this.servletClass = servletClasse;
        this.servletMapping = servletMapping;
    }

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public String getServletClasse() {
        return servletClass;
    }

    public void setServletClasse(String servletClasse) {
        this.servletClass = servletClasse;
    }

    public String getServletMapping() {
        return servletMapping;
    }

    public void setServletMapping(String servletMapping) {
        this.servletMapping = servletMapping;
    }
}
