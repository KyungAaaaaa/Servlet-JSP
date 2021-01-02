package controller;

import com.mysql.jdbc.Connection;
import entity.Notice;

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
import java.util.List;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection con = null;

        String server = "localhost"; // MySQL 서버 주소
        String database = "todagtodag"; // MySQL DATABASE 이름
        String user_name = "root"; //  MySQL 서버 아이디
        String password = "123456"; // MySQL 서버 비밀번호
        List<Notice> noticeList = new ArrayList<>();
        // 1.드라이버 로딩
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! <JDBC error> Driver load error: " + e.getMessage());
            e.printStackTrace();
        }

        // 2.연결
        try {
            con = (Connection) DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name, password);
            System.out.println("connect.");
        } catch (SQLException e) {
            System.err.println("con error:" + e.getMessage());
            e.printStackTrace();
        }

        Statement stmt = null;
        try {

            stmt = con.createStatement();
            String query = "Select * from notice;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println();
                String subject = rs.getString("subject");
                String date = rs.getString("regist_day");
                String id = rs.getString("id");
                int hit = rs.getInt("hit");
                int num = rs.getInt("num");
                String content = rs.getString("content");
                String file = rs.getString("file_name");

                noticeList.add(new Notice(subject, date, id, hit, content, num, file));
            }
            request.setAttribute("noticeList", noticeList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);
    }
}
