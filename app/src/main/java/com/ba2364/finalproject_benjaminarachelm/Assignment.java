package com.ba2364.finalproject_benjaminarachelm;


import java.io.Serializable;

public class Assignment implements Serializable {
    private String topic;
    private String yourHomework;
    private int dueDate;
    private boolean done;

    public Assignment() {
    } //Empty constructor required for data read from Firebase

    public Assignment(String topic, String yourHomework, int dueDate, boolean done) {
        this.topic = topic;
        this.yourHomework = yourHomework;
        this.dueDate = dueDate;
        this.done = done;
    }

    public String getTopic() {
        return topic;
    }

    public String getYourHomework() {
        return yourHomework;
    }

    public int getDueDate() {
        return dueDate;
    }

    public boolean getDone() {
        return done;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setYourHomework(String yourHomework) {
        this.yourHomework = yourHomework;
    }

    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return yourHomework + " " + dueDate + " " + done;
    }
}