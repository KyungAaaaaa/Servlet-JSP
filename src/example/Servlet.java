package example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(urlPatterns = "/SignUp")
public class Servlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8"); //text파일을 웹브라우저에서 html로 인식하게 설정, 문자인코딩
        //request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        int age = 0;
        if (request.getParameter("age") != null) age = Integer.parseInt(request.getParameter("age"));
        for (int i = 0; i < age; i++) out.println((i + 1) + " 안녕 Servlet!!<br>");
        out.println(request.getParameter("name"));

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        String gender = request.getParameter("gender");
        System.out.println(name);
        System.out.println(pass);
        System.out.println(gender);

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            System.out.println("name : " + parameterNames.nextElement());
        }
    }
}
