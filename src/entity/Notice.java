package entity;

public class Notice {
    private String subject;
    private String date;
    private String id;
    private int hit;
    private String content;

    public Notice() {
    }

    public Notice(String subject, String date, String id, int hit, String content) {
        this.subject = subject;
        this.date = date;
        this.id = id;
        this.hit = hit;
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
