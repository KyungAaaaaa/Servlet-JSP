package controller;

import com.mysql.jdbc.Connection;
import entity.Notice;
import service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NoticeService service = new NoticeService();
        String field = "";
        String query = null;
        int page = 1;
        if (request.getParameter("p") != null)
            page = Integer.parseInt(request.getParameter("p"));

        if (request.getParameter("f") != null) {
            switch (request.getParameter("f")) {
                case "title":
                    field = "subject";
                    break;
                case "writerId":
                    field = "id";
                    break;
                default:
                    break;
            }
        } else
            field = "subject";

        if (request.getParameter("q") != null)
            query = request.getParameter("q");


        List<Notice> noticeList = service.getNoticeList();
        request.setAttribute("noticeList", noticeList);

        request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);
    }
}
