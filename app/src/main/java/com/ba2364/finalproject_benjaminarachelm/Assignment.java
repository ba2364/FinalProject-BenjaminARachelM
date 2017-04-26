package com.ba2364.finalproject_benjaminarachelm;

import java.io.Serializable;

public class Assignment implements Serializable {
    private String id;
    private String topic;
    private String yourHomework;
    private long dueDate;
    private boolean done;

    public Assignment() {
    } //Empty constructor required for data read from Firebase

    public Assignment(String id, String topic, String yourHomework, long dueDate, boolean done) {
        this.id = id;
        this.topic = topic;
        this.yourHomework = yourHomework;
        this.dueDate = dueDate;
        this.done = done;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getYourHomework() {
        return yourHomework;
    }

    public void setYourHomework(String yourHomework) {
        this.yourHomework = yourHomework;
    }

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=' " + id + '\'' +
                ", topic='" + topic + '\'' +
                ", assignment='" + yourHomework + '\'' +
                ", due date='" + dueDate + '\'' +
                ", done='" + done + '\'' +
                '}';
    }
}