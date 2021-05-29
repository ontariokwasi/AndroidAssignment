package com.example.datingapp.assignment5;

import android.graphics.drawable.Drawable;

public class MatchesModel {
    private String name;
    private Drawable image;

    public MatchesModel(String name, Drawable image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public Drawable getImage() {
        return image;
    }
}
