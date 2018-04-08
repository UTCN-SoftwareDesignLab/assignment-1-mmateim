package DTO;

import java.util.Date;

public class ActivityDTO {
    private String description;
    private Date date;

    public ActivityDTO(String description, Date date) {
        this.description = description;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }
}
