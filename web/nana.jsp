<%--
  Created by IntelliJ IDEA.
  User: Kimkyungah
  Date: 2020-12-17(017)
  Time: 오후 2:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <%
            String _cnt = request.getParameter("cnt");
            int cnt = 10;
            if (_cnt != null && !_cnt.equals(""))
                cnt = Integer.parseInt(_cnt);
            for (int i = 0; i < cnt; i++) {%>
        안녕!!<br>
        <%}%>
    </body>
</html>
