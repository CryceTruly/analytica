package com.crycetruly.journalapp;

/**
 * Created by Elia on 01/07/2018.
 */

public class Entry {
    private int id;

    private String title,grateful,done,feel;
    public Entry() {
    }

    public Entry(int id, String title, String grateful, String done, String feel) {
        this.id = id;
        this.title = title;
        this.grateful = grateful;
        this.done = done;
        this.feel = feel;
    }

    public Entry(String title, String grateful, String done, String feel) {
        this.title = title;
        this.grateful = grateful;
        this.done = done;
        this.feel = feel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGrateful() {
        return grateful;
    }

    public void setGrateful(String grateful) {
        this.grateful = grateful;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public String getFeel() {
        return feel;
    }

    public void setFeel(String feel) {
        this.feel = feel;
    }
}
