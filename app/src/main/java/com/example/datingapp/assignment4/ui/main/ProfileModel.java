package com.example.datingapp.assignment4.ui.main;

public class ProfileModel {
    private String name;
    private String description;
    private String age;
    private String occupation;

    public ProfileModel(String name, String description, String age, String occupation) {
        this.name = name;
        this.description = description;
        this.age = age;
        this.occupation = occupation;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAge() {
        return age;
    }

    public String getOccupation() {
        return occupation;
    }
}
