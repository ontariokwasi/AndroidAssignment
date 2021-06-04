package com.example.androidapp_development.assignment6;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SettingsDao {

    @Query("Select * from usersettings")
    List<UserSettings> loadSettings();

    @Insert
    void insertAll(UserSettings... settings);

    @Update
    void update(UserSettings settings);
}
