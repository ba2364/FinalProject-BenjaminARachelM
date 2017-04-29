package com.ba2364.finalproject_benjaminarachelm;

import java.io.Serializable;

public class Assignment implements Serializable {
    public String id;
    public String topic;
    public String yourHomework;
    public long dueDate;
    public boolean done;

    public Assignment() {
    } //Empty constructor required for data read from Firebase

    public Assignment(String id, String topic, String yourHomework, long dueDate, boolean done) {
        this.id = id;
        this.topic = topic;
        this.yourHomework = yourHomework;
        this.dueDate = dueDate;
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