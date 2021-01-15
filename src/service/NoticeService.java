package service;

import entity.Notice;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoticeService {
    Connection con = null;
    String server = "localhost"; // MySQL 서버 주소
    String database = "todagtodag"; // MySQL DATABASE 이름
    String user_name = "root"; //  MySQL 서버 아이디
    String password = "123456"; // MySQL 서버 비밀번호

    public List<Notice> getNoticeList() {
        return getNoticeList("subject", "", 1);
    }

    public List<Notice> getNoticeList(int page) {
        return getNoticeList("subject", "", page);
    }

    public List<Notice> getNoticeList(String field, String query, int page) {
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

        List<Notice> noticeList = new ArrayList<>();
        String sql = "";
        PreparedStatement pstmt = null;
        int pageContentCount = 2;
        try {
            sql = "select @rownum:=@rownum+1 as no,Notice.* from notice where (@rownum:=0)=0 and " + field + " like ? order by regist_day desc limit ?,?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + query + "%");
            pstmt.setInt(2, (page - 1) * pageContentCount);
            pstmt.setInt(3, pageContentCount);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int no = rs.getInt("no");
                int num = rs.getInt("num");
                String subject = rs.getString("subject");
                Date date = rs.getDate("regist_day");
                String id = rs.getString("id");
                int hit = rs.getInt("hit");
                String content = rs.getString("content");
                String file = rs.getString("file_name");
                noticeList.add(new Notice(subject, date, id, hit, content, num, no, file));
            }


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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return noticeList;
    }

    public int getNoticeCount() {
        return getNoticeCount("title", "");
    }

    public int getNoticeCount(String field, String query) {
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
        int count = 0;
        // 3.해제
        String sql = "select count(num) as count from notice where " + field + " ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + query + "%");
            ResultSet rs = pstmt.executeQuery();
            count = rs.getInt("count");
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return count;
    }

    public Notice getNotice(int num) {
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
        Notice notice = null;
        String sql = "select * from notice where num=?";
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, num);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String subject = rs.getString("subject");
                Date date = rs.getDate("regist_day");
                String id = rs.getString("id");
                int hit = rs.getInt("hit");
                String content = rs.getString("content");
                String file = rs.getString("file_name");

                notice = new Notice(subject, date, id, hit, content, num, file);
            }
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return notice;
    }

    public Notice getNextNotice(int num) {
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
        Notice notice = null;

        String sql = "Select Notice.* from notice WHERE str_to_date(regist_day,'%Y-%m-%d') >(select str_to_date(regist_day,'%Y-%m-%d') from notice where num=?) limit 1;";

        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, num);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String subject = rs.getString("subject");
                Date date = rs.getDate("regist_day");
                String id = rs.getString("id");
                int hit = rs.getInt("hit");
                String content = rs.getString("content");
                String file = rs.getString("file_name");

                notice = new Notice(subject, date, id, hit, content, num, file);
            }
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return notice;
    }

    public Notice getPrevNotice(int num) {
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
        Notice notice = null;
        String sql = "Select Notice.* from notice WHERE str_to_date(regist_day,'%Y-%m-%d') <(select str_to_date(regist_day,'%Y-%m-%d') from notice where num=?) order by regist_day desc limit 1;";
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, num);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String subject = rs.getString("subject");
                Date date = rs.getDate("regist_day");
                String id = rs.getString("id");
                int hit = rs.getInt("hit");
                String content = rs.getString("content");
                String file = rs.getString("file_name");

                notice = new Notice(subject, date, id, hit, content, num, file);
            }
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return notice;
    }
}
