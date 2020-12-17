package example;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        req.setAttribute("result", result);

        String[] str = {"안녕하세요", " 김경아", "입니다"};
        req.setAttribute("strArray", str);

        Map<String, Object> map = new HashMap<>();
        map.put("first", "apple");
        map.put("second", "banana");
        map.put("third", "grape");
        req.setAttribute("map", map);
        //redirect 새로 요청할때
        //forward  내용이 이어지는것
        RequestDispatcher dispatcher = req.getRequestDispatcher("nana.jsp");
        dispatcher.forward(req, resp);
    }

}
