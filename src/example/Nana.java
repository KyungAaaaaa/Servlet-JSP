package example;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/nana")
public class Nana extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String _cnt = req.getParameter("cnt");
        int cnt = 10;

        if (_cnt != null && !_cnt.equals(""))
            cnt = Integer.parseInt(_cnt);

        String result;
        if (cnt % 2 == 0) result = "짝수";
        else result = "홀수";


        //redirect 새로 요청할때
        //forward  내용이 이어지는것
        req.setAttribute("result",result);
        RequestDispatcher dispatcher = req.getRequestDispatcher("nana.jsp");
        dispatcher.forward(req,resp);
    }

}
