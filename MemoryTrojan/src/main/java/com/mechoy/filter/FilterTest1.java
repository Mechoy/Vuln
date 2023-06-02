package com.mechoy.filter; /**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/5/23
 * Description
 */

import org.apache.tomcat.util.descriptor.web.FilterDef;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(filterName = "FilterTest1")
public class FilterTest1 implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println(">>>>> FilterTest1 执行");
        chain.doFilter(request, response);
    }
}
