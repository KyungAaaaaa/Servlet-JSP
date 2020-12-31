package controller;

import entity.Notice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;

        String server = "localhost"; // MySQL 서버 주소
        String database = "todagtodag"; // MySQL DATABASE 이름
        String user_name = "root"; //  MySQL 서버 아이디
        String password = "123456"; // MySQL 서버 비밀번호

        // 1.드라이버 로딩
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! <JDBC error> Driver load error: " + e.getMessage());
            e.printStackTrace();
        }

        // 2.연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name, password);
            System.out.println("connect.");
        } catch (SQLException e) {
            System.err.println("con error:" + e.getMessage());
            e.printStackTrace();
        }

        int num = Integer.parseInt(request.getParameter("id"));
        String query = "Select * from notice where num=?;";
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, num);
            ResultSet rs = pstmt.executeQuery();
            rs.next();


            String subject = rs.getString("subject");
            String date = rs.getString("regist_day");
            String id = rs.getString("id");
            int hit = rs.getInt("hit");
            String content = rs.getString("content");

            Notice notice = new Notice(subject, date, id, hit, content);
            request.setAttribute("notice", notice);
            // 3.해제
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
            }
            request.setAttribute("subject", subject);
            request.setAttribute("date", date);
            request.setAttribute("id", id);
            request.setAttribute("hit", hit);
            request.setAttribute("content", content);
            pstmt = con.prepareStatement(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //redirect
        //forward
        request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp").forward(request, response);
    }
}
