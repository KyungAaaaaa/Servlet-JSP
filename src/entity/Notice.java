package entity;

import java.util.Date;

public class Notice {
    private String subject;
    private Date date;
    private String id;
    private int hit;
    private String content;
    private int num;
    private String file;

    public Notice() {
    }

    public Notice(String subject, Date date, String id, int hit, String content, int num, String file) {
        this.subject = subject;
        this.date = date;
        this.id = id;
        this.hit = hit;
        this.content = content;
        this.num = num;
        this.file = file;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "subject='" + subject + '\'' +
                ", date='" + date + '\'' +
                ", id='" + id + '\'' +
                ", hit=" + hit +
                ", content='" + content + '\'' +
                '}';
    }
}
