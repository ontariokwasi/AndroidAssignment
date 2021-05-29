package com.example.datingapp.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.datingapp.R;

public class Profile_Activity extends AppCompatActivity {
    TextView nameView, descriptionView, ageView, occupationView;
    Button backBtn;
    ImageView profileImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String name = getIntent().getStringExtra("name");
        String description = getIntent().getStringExtra("description");
        String occupation = getIntent().getStringExtra("occupation");
        String age = getIntent().getStringExtra("age");

        nameView = findViewById(R.id.profile_name);
        descriptionView = findViewById(R.id.profile_description);
        ageView = findViewById(R.id.profile_age);
        occupationView = findViewById(R.id.profile_occupation);
        backBtn = findViewById(R.id.profile_backBtn);
        profileImageView = findViewById(R.id.profile_image);
        profileImageView.setImageDrawable(getDrawable(R.mipmap.profile_image_round));
        nameView.setText(name);
        descriptionView.setText(description);
        occupationView.setText(occupation);
        ageView.setText(age+" years");

        backBtn.setOnClickListener(view->{finish();});
    }
}