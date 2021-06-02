package com.example.datingapp.assignment6;

import androidx.room.Dao;
import androidx.room.Delete;
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

    @Query("Delete from usersettings where 1")
    void deleteAll();

    @Update
    void update(UserSettings settings);
}
