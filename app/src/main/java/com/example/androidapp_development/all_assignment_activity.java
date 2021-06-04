package com.example.androidapp_development;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidapp_development.assignment2.signup2MainActivity;
import com.example.androidapp_development.assignment3.signup3Activity;
import com.example.androidapp_development.assignment4.Assignment4Signup;
import com.example.androidapp_development.assignment6.FirestoreUtil;
import com.example.androidapp_development.assignment6.LocationService;
import com.example.androidapp_development.assignment6.SettingsRepository;

public class all_assignment_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_assignment_activity);
        findViewById(R.id.assignment2).setOnClickListener(view -> {
            startActivity(new Intent(all_assignment_activity.this, signup2MainActivity.class));
        });
        findViewById(R.id.assignment3).setOnClickListener(view -> startActivity(new Intent(all_assignment_activity.this, signup3Activity.class)));

        findViewById(R.id.assignment4).setOnClickListener(view -> {
            startActivity(new Intent(all_assignment_activity.this, Assignment4Signup.class));
        });
        findViewById(R.id.assignment4).setOnClickListener(view -> {
            startActivity(new Intent(all_assignment_activity.this, Assignment4Signup.class));
        });

        new Thread(()-> {
            LocationService.init(this);
            FirestoreUtil.initData();
            SettingsRepository.init(getApplicationContext());
        }).start();

    }
}