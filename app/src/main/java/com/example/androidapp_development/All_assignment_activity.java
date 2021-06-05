package com.example.androidapp_development;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidapp_development.assignment2.signup2MainActivity;
import com.example.androidapp_development.assignment3.signup3Activity;
import com.example.androidapp_development.assignment4.Assignment4Signup;
import com.example.androidapp_development.assignment6.FirestoreUtil;
import com.example.androidapp_development.assignment6.LocationService;
import com.example.androidapp_development.assignment6.SettingsRepository;

public class All_assignment_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_assignment_activity);
        findViewById(R.id.assignment2).setOnClickListener(view -> {
            startActivity(new Intent(All_assignment_activity.this, signup2MainActivity.class));
        });
        findViewById(R.id.assignment3).setOnClickListener(view -> startActivity(new Intent(All_assignment_activity.this, signup3Activity.class)));

        findViewById(R.id.assignment4).setOnClickListener(view -> {
            startActivity(new Intent(All_assignment_activity.this, Assignment4Signup.class));
        });
        findViewById(R.id.assignment4).setOnClickListener(view -> {
            startActivity(new Intent(All_assignment_activity.this, Assignment4Signup.class));
        });

        LocationService.init(this);
        new Thread(()-> {
            FirestoreUtil.initData();
            SettingsRepository.init(getApplicationContext());
        }).start();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
            LocationService.init(this);
        }
    }
}