package model;

import java.util.Date;

public class Activity {

    private Long id;
    private Date date;
    private String description;
    private Long userId;

    public Activity(Date date, String description, Long userId) {
        this.date = date;
        this.description = description;
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
