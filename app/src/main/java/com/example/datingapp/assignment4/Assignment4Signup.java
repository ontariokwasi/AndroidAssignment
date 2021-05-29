package com.example.datingapp.assignment4;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.datingapp.R;
import com.example.datingapp.assignment3.Profile_Activity;

public class Assignment4Signup extends AppCompatActivity {

    private EditText nameField, ageField, descriptionField, occupationField;
    private Button signupBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment3);

        nameField = (EditText) findViewById(R.id.name);
        descriptionField = (EditText) findViewById(R.id.description);
        ageField = (EditText) findViewById(R.id.age);
        occupationField = (EditText) findViewById(R.id.occupation);
        signupBtn = (Button) findViewById(R.id.create_profile);
        signupBtn.setOnClickListener(view->{
            String name = nameField.getText().toString();
            String description = descriptionField.getText().toString();
            String age = ageField.getText().toString();
            String occupation = occupationField.getText().toString();
            if(age != null && !age.isEmpty()){
                if(Integer.parseInt(age) < 18){
                    ageField.setTextColor(Color.parseColor("red"));
                    Toast.makeText(Assignment4Signup.this,"You're too young!", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(this, Assignment4Activity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("description", description);
                    intent.putExtra("occupation", occupation);
                    intent.putExtra("age", age);
                    startActivity(intent);
                    nameField.setText("");
                    ageField.setText("");
                    descriptionField.setText("");
                    occupationField.setText("");
                }
            }
            else {
                Toast.makeText(Assignment4Signup.this,"Please enter your age", Toast.LENGTH_LONG).show();
            }
        });
    }
}