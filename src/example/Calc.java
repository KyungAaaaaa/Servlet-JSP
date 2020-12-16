package example;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/calc")
public class Calc extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext application = request.getServletContext();
        String v_ = request.getParameter("v");
        String op = request.getParameter("op");
        int v = 0;
        if (!v_.equals("")) v = Integer.parseInt(v_);
        if (op.equals("=")) {

            int x = (Integer) application.getAttribute("value");
            int y = v;

            if (application.getAttribute("op").equals("+")) response.getWriter().println(x + y);
            else response.getWriter().println(x - y);

        } else {
            application.setAttribute("value", v);
            application.setAttribute("op", op);
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}