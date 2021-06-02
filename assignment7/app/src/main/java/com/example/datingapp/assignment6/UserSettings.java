package com.example.datingapp.assignment6;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserSettings {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String reminderTime;
    private String searchDistance;
    private String gender;
    private boolean isPublicAccount;
    private String interestedAgeRange;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }

    public String getSearchDistance() {
        return searchDistance;
    }

    public void setSearchDistance(String searchDistance) {
        this.searchDistance = searchDistance;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isPublicAccount() {
        return isPublicAccount;
    }

    public void setPublicAccount(boolean publicAccount) {
        isPublicAccount = publicAccount;
    }

    public String getInterestedAgeRange() {
        return interestedAgeRange;
    }

    public void setInterestedAgeRange(String interestedAgeRange) {
        this.interestedAgeRange = interestedAgeRange;
    }
}
