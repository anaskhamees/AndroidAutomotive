package com.example.cakeslist;

public class Cake {
    private int Thumbnail;
    private String title;
    private String description;

    public int getThumbnail() {
        return Thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public Cake(int thumbnail, String title, String description) {
        Thumbnail = thumbnail;
        this.title = title;
        this.description = description;
    }
}
