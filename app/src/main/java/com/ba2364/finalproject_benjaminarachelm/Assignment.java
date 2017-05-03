package com.ba2364.finalproject_benjaminarachelm;

import java.io.Serializable;
import java.util.Date;

public class Assignment implements Serializable {
    public String id;
    public String topic;
    public String yourHomework;
    public boolean done;
    public Date date;
    public int dateDay;
    public int dateMonth;
    public int dateYear;

    public Assignment() {
    } 

    public Assignment(String id, String topic, String yourHomework, Date date, int dateDay, int dateMonth, int dateYear, boolean done) {
        this.id = id;
        this.topic = topic;
        this.yourHomework = yourHomework;
        this.done = done;
        this.dateDay = dateDay;
        this.dateMonth = dateMonth;
        this.dateYear = dateYear;
        this.date = date;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=' " + id + '\'' +
                ", topic='" + topic + '\'' +
                ", assignment='" + yourHomework + '\'' +
                ", due date day='" + dateDay + '\'' +
                ", due date month='" + dateMonth + '\'' +
                ", due date year='" + dateYear + '\'' +
                ", done='" + done + '\'' +
                '}';
    }
}