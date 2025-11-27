package com.example.safetyapp;

/**
 * Simple data model for a safety tip used by the adapter.
 */
public class SafetyTipModel {
    public String title;
    public String description;
    public int iconResId;
    public int desc;
    public int icon;

    public SafetyTipModel(String title, String description, int iconResId) {
        this.title = title;
        this.description = description;
        this.iconResId = iconResId;
    }
}
