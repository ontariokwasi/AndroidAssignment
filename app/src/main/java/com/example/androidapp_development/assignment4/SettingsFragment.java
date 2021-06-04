package com.example.androidapp_development.assignment4;

import android.app.TimePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.example.androidapp_development.R;
import com.example.androidapp_development.assignment6.AppDatabase;
import com.example.androidapp_development.assignment6.MatchesViewModel;
import com.example.androidapp_development.assignment6.SettingsDao;
import com.example.androidapp_development.assignment6.SettingsViewModel;
import com.example.androidapp_development.assignment6.UserSettings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SettingsFragment extends Fragment {
    public SettingsFragment() {
        // Required empty public constructor
    }

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

        List<String> distances = new ArrayList<>(Arrays.asList("10","20", "50", "100", "300","500","700","1000"));
        List<String> gender = new ArrayList<>(Arrays.asList("Male", "Female", "Other"));
        List<String> ageRanges = new ArrayList<>(Arrays.asList("18-25", "26-30", "31-40", "45+"));

        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        SettingsViewModel settingsViewModel = new ViewModelProvider(requireActivity()).get(SettingsViewModel.class);
        UserSettings settings = settingsViewModel.getUserSettingsLiveData().getValue();
        MatchesViewModel matchesViewModel = new ViewModelProvider(requireActivity()).get(MatchesViewModel.class);

        TextView reminderTimeView = view.findViewById(R.id.reminder_time_view);
        ImageButton editReminderBtn = view.findViewById(R.id.editReminderTime);
        Spinner maxDistanceSearchSpinner = view.findViewById(R.id.search_distance);
        Spinner genderSpinner = view.findViewById(R.id.gender);
        Spinner ageRangeSpinner = view.findViewById(R.id.interested_age_range);
        Switch accountVisibilitySwitch = view.findViewById(R.id.togglePublicPrivate);

        maxDistanceSearchSpinner.setAdapter(new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item, distances.stream().map(d->String.format("%s miles",d)).collect(Collectors.toList())));
        maxDistanceSearchSpinner.setSelection(((ArrayAdapter<String>)(maxDistanceSearchSpinner.getAdapter())).getPosition(settings.getSearchDistance()+" miles"));

        genderSpinner.setAdapter(new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item, gender));
        genderSpinner.setSelection(((ArrayAdapter<String>)(genderSpinner.getAdapter())).getPosition(settings.getGender()));

        ageRangeSpinner.setAdapter(new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item, ageRanges));
        ageRangeSpinner.setSelection(((ArrayAdapter<String>)(ageRangeSpinner.getAdapter())).getPosition(settings.getInterestedAgeRange()));

        accountVisibilitySwitch.setChecked(settings.isPublicAccount());
        reminderTimeView.setText(settings.getReminderTime());

        editReminderBtn.setOnClickListener(v->{
          new TimePickerDialog(getContext(), (pickerView, hour, minute)->{
              String time  = String.format("%02d:%02d",hour, minute);
              reminderTimeView.setText(time);
              settings.setReminderTime(time);
              settingsViewModel.saveSettings(settings);
          }, 12,0,true).show();
        });

        maxDistanceSearchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!distances.get(position).equals(settings.getSearchDistance()) ){
                    settings.setSearchDistance(distances.get(position));
                    settingsViewModel.saveSettings(settings);
                    matchesViewModel.triggerSettingsChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!gender.get(position).equals(settings.getSearchDistance())){
                    settings.setGender(gender.get(position));
                    settingsViewModel.saveSettings(settings);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ageRangeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(!ageRanges.get(position).equals(settings.getInterestedAgeRange())){
                    settings.setInterestedAgeRange(ageRanges.get(position));
                    settingsViewModel.saveSettings(settings);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        accountVisibilitySwitch.setOnCheckedChangeListener((v, status)->{
            settings.setPublicAccount(status);
            settingsViewModel.saveSettings(settings);
        });

        return view;
    }

}