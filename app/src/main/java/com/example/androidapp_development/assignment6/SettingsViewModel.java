package com.example.androidapp_development.assignment6;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class SettingsViewModel extends ViewModel {

    private final MutableLiveData<UserSettings> userSettingsLiveData = new MutableLiveData<>();

    public SettingsViewModel(){
        userSettingsLiveData.setValue(SettingsRepository.getSettings());
    }
    public MutableLiveData<UserSettings> getUserSettingsLiveData() {
        return userSettingsLiveData;
    }

    public void saveSettings(UserSettings settings){
        userSettingsLiveData.setValue(settings);
        SettingsRepository.updateSettings(settings);
    }
}
