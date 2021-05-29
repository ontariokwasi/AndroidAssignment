package com.example.datingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.datingapp.assignment2.Assignment2MainActivity;
import com.example.datingapp.assignment3.Assignment3Activity;
import com.example.datingapp.assignment4.Assignment4Signup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        findViewById(R.id.assignment2).setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, Assignment2MainActivity.class));
        });
        findViewById(R.id.assignment3).setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, Assignment3Activity.class));
        });

        findViewById(R.id.assignment4).setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, Assignment4Signup.class));
        });

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, MainFragment.newInstance())
//                    .commitNow();
//        }
    }
}