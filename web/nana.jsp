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
        <%--        페이지 객체에 담은 값을 사용할수있다    --%>
        <% pageContext.setAttribute("aa", "hello");%>

        <%--        <%=request.getAttribute("result")%> 입니다.<br>--%>

        <%--        EL (Expression Language)
                  el 저장소 (내장객체)에서 꺼내는 간단한 표현방법
                우선순위 : page - request - session - application
                특정 저장소에서 꺼낼때는 scope사용               --%>
        ${requestScope.result}
        ${result} 입니다.<br>
        ${strArray[0]}${strArray[1]}${strArray[2]}<br>
        ${map.first}<br>
        ${map.third}<br>

        ${aa}<br>
        ${header.accept}

        <%-- EL 연산자 --%>
        ${param.cnt eq 5}<br>
        ${empty param.cnt}<br>

    </body>
</html>
