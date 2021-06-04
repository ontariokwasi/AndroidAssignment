package com.example.androidapp_development.assignment6;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

public class SettingsRepository {
    private static AppDatabase db;
    private static UserSettings settings;

    public static void init(Context context){
        db = Room.databaseBuilder(context, AppDatabase.class, "settings-db").build();
        SettingsDao settingsDao = db.settingsDao();
        List<UserSettings> settingsList = settingsDao.loadSettings();
        if(!settingsList.isEmpty()){
            settings = settingsList.get(0);
        }
        else{
            settings = new UserSettings();
            settings.setSearchDistance(String.valueOf(Integer.MAX_VALUE));
            settings.setReminderTime("00:00"); // all default values
        }
    }

    public static void updateSettings(UserSettings settings){
        new Thread(()->{
            try {
                SettingsDao settingsDao = db.settingsDao();
                settingsDao.update(settings);
            }catch (Exception e){
                e.printStackTrace();
            }

        }).start();
    }

    public static UserSettings getSettings() {
        return settings;
    }
}
