package example;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/calculator")
public class Calculator extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value = request.getParameter("value");
        String op = request.getParameter("op");
        String dot = request.getParameter("dot");
        String exp = "";
        Cookie[] cookies = request.getCookies();
        ArrayList<String> array = new ArrayList<>();
        if (cookies != null)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("exp")) {
                    exp = cookie.getValue();
                    break;
                }
            }
        if (op != null && op.equals("=")) {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            try {
                exp = String.valueOf(engine.eval(exp));
            } catch (ScriptException e) {
                e.printStackTrace();
            }

        } else if (op != null && op.equals("C")) {
            exp = "";

        } else {
            exp += (value == null) ? "" : value;
            exp += (op == null) ? "" : op;
            exp += (dot == null) ? "" : dot;
        }
        Cookie expCookie = new Cookie("exp", exp);
        if (op != null && op.equals("C")) expCookie.setMaxAge(0);
        expCookie.setPath("/");
        response.addCookie(expCookie);
        response.sendRedirect("calculator");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();


        String exp = "0";
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("exp")) exp = cookie.getValue();
            }

        out.printf("<!DOCTYPE html>");
        out.printf("<html>");
        out.printf("	<head>");
        out.printf("		<meta charset = \"UTF - 8\">");
        out.printf("		<title> 계산기 </title>");
        out.printf("		<style>");
        out.printf("                input{");
        out.printf("            width:50px;");
        out.printf("            height:50px;");
        out.printf("        }");
        out.printf("			.output{");
        out.printf("            height: 50 px;");
        out.printf("            background: #e9e9e9;");
        out.printf("            font-size:24px;");
        out.printf("            font-weight:bold;");
        out.printf("            text-align:right;");
        out.printf("            padding: 0 10 px;");
        out.printf("        }");
        out.printf("		</style>");
        out.printf("	</head>");
        out.printf("	<body>");
        out.printf("		<form method = \"post\">");
        out.printf("			<table>");
        out.printf("				<tr>");
        out.printf("					<td colspan = \"4\" class=\"output\"> %s <td>", exp);
        out.printf("				</tr>");
        out.printf("				<tr>");
        out.printf("					<td><input type = \"submit\" name = \"op\" value = \"CE\"></td>");
        out.printf("					<td><input type = \"submit\" name = \"op\" value = \"C\"></td>");
        out.printf("					<td><input type = \"submit\" name = \"op\" value = \"B\"></td>");
        out.printf("					<td><input type = \"submit\" name = \"op\" value = \"/\"></td>");
        out.printf("				</tr>");
        out.printf("				<tr>");
        out.printf("					<td><input type = \"submit\" name = \"value\" value = \"7\"></td>");
        out.printf("					<td><input type = \"submit\" name = \"value\" value = \"8\"></td>");
        out.printf("					<td><input type = \"submit\" name = \"value\" value = \"9\"></td>");
        out.printf("					<td><input type = \"submit\" name = \"op\" value = \"*\"></td>");
        out.printf("				</tr>");
        out.printf("				<tr>");
        out.printf("					<td><input type = \"submit\" name = \"value\" value = \"4\"></td>");
        out.printf("					<td><input type = \"submit\" name = \"value\" value = \"5\"></td>");
        out.printf("					<td><input type = \"submit\" name = \"value\" value = \"6\"></td>");
        out.printf("					<td><input type = \"submit\" name = \"op\" value = \"-\"></td>");
        out.printf("				</tr>");
        out.printf("				<tr>");
        out.printf("					<td><input type = \"submit\" name = \"value\" value = \"1\"></td>");
        out.printf("					<td><input type = \"submit\" name = \"value\" value = \"2\"></td>");
        out.printf("					<td><input type = \"submit\" name = \"value\" value = \"3\"></td>");
        out.printf("					<td><input type = \"submit\" name = \"op\" value = \"+\"></td>");
        out.printf("				</tr>");
        out.printf("				<tr>");
        out.printf("					<td></td>");
        out.printf("					<td><input type = \"submit\" name = \"value\" value =\"0\"></td>");
        out.printf("					<td><input type = \"submit\" name = \"dot\" value = \".\"></td>");
        out.printf("					<td><input type = \"submit\" name = \"op\" value = \"=\"></td>");
        out.printf("				</tr>");
        out.printf("			</table>");
        out.printf("		</form>");
        out.printf("	</body>");
        out.printf("</html>");
    }
}
