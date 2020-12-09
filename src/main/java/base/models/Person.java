package base.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;

public class Person {
    private int id;

    private int reason;

    @DateTimeFormat(pattern = "dd.MM.yy")
    private Date start_date;

    @Min(value = 1, message = "Enter the number")
    private int duration;

    private boolean discounted;

    @Size(max = 1024)
    private String description;

    public Person() {

    }

    public Person(int id, int reason, Date start_date, int duration, boolean discounted, String description) {
        this.id = id;
        this.reason = reason;
        this.start_date = start_date;
        this.duration = duration;
        this.discounted = discounted;
        this.description = description;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getReason() {
        return reason;
    }
    public void setReason(int reason) {
        this.reason = reason;
    }
    public Date getStart_date() {
        return start_date;
    }
    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public boolean isDiscounted() {
        return discounted;
    }
    public void setDiscounted(boolean discounted) {
        this.discounted = discounted;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}