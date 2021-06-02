package com.example.datingapp.assignment4;

import android.app.TimePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.datingapp.R;
import com.example.datingapp.assignment6.AppDatabase;
import com.example.datingapp.assignment6.SettingsDao;
import com.example.datingapp.assignment6.UserSettings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {
    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AppDatabase db = Room.databaseBuilder(getContext(), AppDatabase.class, "settings-db").build();
        SettingsDao settingsDao = db.settingsDao();
        UserSettings settings = new UserSettings();
        List<String> distances = new ArrayList<>(Arrays.asList("20 miles", "50 miles", "100 miles", "100+ miles"));
        List<String> gender = new ArrayList<>(Arrays.asList("Male", "Female", "Other"));
        List<String> ageRanges = new ArrayList<>(Arrays.asList("18-25", "26-30", "31-40", "45+"));

        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        new RetrieveData(view, distances, gender, ageRanges, settings).execute();

        TextView reminderTimeView = view.findViewById(R.id.reminder_time_view);
        ImageButton editReminderBtn = view.findViewById(R.id.editReminderTime);
        Spinner maxDistanceSearchSpinner = view.findViewById(R.id.search_distance);
        Spinner genderSpinner = view.findViewById(R.id.gender);
        Spinner ageRangeSpinner = view.findViewById(R.id.interested_age_range);
        Switch accountVisibilitySwitch = view.findViewById(R.id.togglePublicPrivate);

        editReminderBtn.setOnClickListener(v->{
          new TimePickerDialog(getContext(), (pickerView, hour, minute)->{
              reminderTimeView.setText(hour+":"+minute);
              settings.setReminderTime(hour+":"+minute);
              updateSettings(settingsDao,settings);
          }, 12,0,true).show();
        });

        maxDistanceSearchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(distances.get(position) != settings.getSearchDistance()){
                    settings.setSearchDistance(distances.get(position));
                    updateSettings(settingsDao, settings);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(gender.get(position) != settings.getSearchDistance()){
                    settings.setGender(gender.get(position));
                    updateSettings(settingsDao, settings);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ageRangeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(ageRanges.get(position) != settings.getInterestedAgeRange()){
                    settings.setInterestedAgeRange(ageRanges.get(position));
                    updateSettings(settingsDao, settings);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        accountVisibilitySwitch.setOnCheckedChangeListener((v, status)->{
            settings.setPublicAccount(status);
            updateSettings(settingsDao,settings);
        });

        return view;
    }

    private class RetrieveData extends AsyncTask<Void, Void, List<UserSettings>>{
        View view;
        List<String> distances;
        List<String> gender;
        List<String> ageRanges;
        UserSettings settingsRef;
        RetrieveData(View view, List<String> distances, List<String> gender, List<String> ageRanges, UserSettings settingsRef){
            this.view = view;
            this.ageRanges = ageRanges;
            this.gender = gender;
            this.distances = distances;
            this.settingsRef = settingsRef;

        }
        @Override
        protected List<UserSettings> doInBackground(Void... params) {
            AppDatabase db = Room.databaseBuilder(getContext(), AppDatabase.class, "settings-db").build();
            SettingsDao settingsDao = db.settingsDao();
            List<UserSettings> settingsList = settingsDao.loadSettings();
            if(settingsList.isEmpty()){
                UserSettings settings = new UserSettings();
                settings.setReminderTime("00:00");
                settingsDao.insertAll(settings);
                settingsList.add(settings);
            }
            return settingsList;
        }

        @Override
        protected void onPostExecute(List<UserSettings> userSettings) {

            UserSettings settings =  userSettings.get(0);

            String selectedGender = settings.getGender();
            String selectedAgeRange = settings.getInterestedAgeRange();
            String selectedDistance = settings.getSearchDistance();
            String reminderTime = settings.getReminderTime();
            boolean isPublicAccount = settings.isPublicAccount();

            TextView reminderTimeView = view.findViewById(R.id.reminder_time_view);
            Spinner maxDistanceSearchSpinner = view.findViewById(R.id.search_distance);
            Spinner genderSpinner = view.findViewById(R.id.gender);
            Spinner ageRangeSpinner = view.findViewById(R.id.interested_age_range);
            Switch accountVisibilitySwitch = view.findViewById(R.id.togglePublicPrivate);

            reminderTimeView.setText(reminderTime);

            reorderList(distances, selectedDistance);
            reorderList(ageRanges, selectedAgeRange);
            reorderList(gender, selectedGender);
            maxDistanceSearchSpinner.setAdapter(new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item, distances));
            genderSpinner.setAdapter(new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item, gender));
            ageRangeSpinner.setAdapter(new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item, ageRanges));
            accountVisibilitySwitch.setChecked(isPublicAccount);
            settingsRef.setReminderTime(settings.getReminderTime());
            settingsRef.setSearchDistance(settings.getSearchDistance());
            settingsRef.setGender(settings.getGender());
            settingsRef.setPublicAccount(settings.isPublicAccount());
            settingsRef.setId(settings.getId());
        }
    }
    private void reorderList(List<String> list, String selected){
        if(selected != null && list.remove(selected)){
            list.add(0,selected);
        }
    }

    private void updateSettings(SettingsDao settingsDao, UserSettings settings){
        new Thread(()->{
            try {
                settingsDao.update(settings);
            }catch (Exception e){
                e.printStackTrace();
            }

        }).start();
    }
}