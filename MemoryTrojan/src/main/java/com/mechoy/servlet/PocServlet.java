package com.mechoy.servlet; /**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/5/10
 * Description
 */

import com.google.gson.Gson;
import com.mechoy.bean.ResponseBean;
import com.mechoy.service.IndexService;
import com.mechoy.service.impl.IndexServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class PocServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
<<<<<<< HEAD
        String poc = request.getParameter("poc");
        IndexService indexService = new IndexServiceImpl();
        ResponseBean responseBean = new ResponseBean();
        if(indexService.pocExec(poc)){
            responseBean.setCode("000000");
            responseBean.setMessage("success");
        }else{
            responseBean.setCode("999999");
        }
        String res = new Gson().toJson(responseBean);
        response.getWriter().write(res);
=======
//        String poc = request.getParameter("poc");
//        IndexService indexService = new IndexServiceImpl();
//        if(indexService.pocTest(poc)){
//            return;
//        }else{
//            return;
//        }
        System.out.println(request.getParameter("poc"));

>>>>>>> e95591830a43e11b62a9e60b0b72dab6d1d99cec
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
