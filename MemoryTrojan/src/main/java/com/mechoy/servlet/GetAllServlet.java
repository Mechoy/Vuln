package com.mechoy.servlet; /**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/5/23
 * Description
 */

import com.google.gson.Gson;
import com.mechoy.bean.ServletBean;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GetAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<? extends ServletRegistration> servletRegistrations = request.getServletContext().getServletRegistrations().values();
        ArrayList<ServletBean> servletBeans = new ArrayList<>();

        for (ServletRegistration servletRegistration : servletRegistrations) {
            ServletBean servletBean = new ServletBean();
            servletBean.setServletName(servletRegistration.getName());
            servletBean.setServletClasse(servletRegistration.getClassName());
            servletBean.setServletMapping(String.valueOf(servletRegistration.getMappings()));
            servletBeans.add(servletBean);
        }

        String allServletList = new Gson().toJson(servletBeans);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(allServletList);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
