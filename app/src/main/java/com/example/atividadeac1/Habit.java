package com.example.atividadeac1;

import java.io.Serializable;

public class Habit implements Serializable {
    private int id;
    private String name;
    private String description;
    private boolean doneToday;

    public Habit(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.doneToday = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDoneToday() {
        return doneToday;
    }

    public void setDoneToday(boolean doneToday) {
        this.doneToday = doneToday;
    }
}
