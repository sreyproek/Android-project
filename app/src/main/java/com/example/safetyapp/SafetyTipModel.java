package com.example.safetyapp;

public class SafetyTipModel {
    private int image;
    private String title;
    private String description;

    public SafetyTipModel(int image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public int getImage() { return image; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
}
