package example;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/calc")
public class Calc extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ServletContext application = request.getServletContext();
//        HttpSession session = request.getSession();

        String v_ = request.getParameter("v");
        String op = request.getParameter("op");
        int v = 0;
        if (!v_.equals("")) v = Integer.parseInt(v_);
        if (op.equals("=")) {
            Cookie[] cookies = request.getCookies();
            int x = 0;
            String op2 = "";
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("value")) x = Integer.parseInt(cookie.getValue());
                if (cookie.getName().equals("op")) op2 = cookie.getValue();
            }
            int y = v;

            if (op2.equals("+")) response.getWriter().println(x + y);
            else response.getWriter().println(x - y);

        } else {
//            session.setAttribute("value", v);
//            session.setAttribute("op", op);
            Cookie valueCookie = new Cookie("value", String.valueOf(v));
            Cookie opCookie = new Cookie("op", op);
            response.addCookie(valueCookie);
            response.addCookie(opCookie);
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
