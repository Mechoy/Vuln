<%@ page import="java.io.IOException" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="org.apache.catalina.core.ApplicationContext" %>
<%@ page import="org.apache.catalina.core.StandardContext" %>
<%@ page import="org.apache.tomcat.util.descriptor.web.FilterDef" %>
<%@ page import="org.apache.tomcat.util.descriptor.web.FilterMap" %>
<%@ page import="java.io.File" %><%--
  Created by IntelliJ IDEA.
  User: mechoy
  Date: 2023/5/11
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%!
    public class FilterTest3 extends FilterDef implements Filter {
        public void init(FilterConfig config) throws ServletException {
        }

        public void destroy() {
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
            System.out.println("动态注册的Filter完工");
            chain.doFilter(request, response);
        }
    }
%>

<%
    // 动态注册filter
    // 1.获取standardContext
    ServletContext servletContext = request.getServletContext();
    Field applicationContextField = servletContext.getClass().getDeclaredField("context");
    applicationContextField.setAccessible(true);
    ApplicationContext applicationContext = (ApplicationContext) applicationContextField.get(servletContext);
    Field standardContextField = applicationContext.getClass().getDeclaredField("context");
    standardContextField.setAccessible(true);
    StandardContext standardContext = (StandardContext) standardContextField.get(applicationContext);

    // 创建filter
    FilterTest3 filterShell = new FilterTest3();
    FilterDef filterDef = new FilterDef();
    filterDef.setFilter(filterShell);
    filterDef.setFilterClass(filterShell.getClass().getName());
    filterDef.setFilterName("FilterTest3");
    standardContext.addFilterDef(filterDef);


    FilterMap filterMap = new FilterMap();
    filterMap.setFilterName("FilterTest3");
    filterMap.addURLPattern("/");
    standardContext.addFilterMap(filterMap);

    System.out.println(servletContext);
    System.out.println("添加Filter成功...");

%>
</body>
</html>
