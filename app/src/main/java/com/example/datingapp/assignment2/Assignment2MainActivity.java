package com.example.datingapp.assignment2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.datingapp.R;

import java.time.LocalDate;

public class Assignment2MainActivity extends AppCompatActivity {
 private EditText nameField, usernameField, emailField, dobField;
 private Button signupBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        nameField = (EditText) findViewById(R.id.name);
        usernameField = (EditText) findViewById(R.id.username);
        emailField = (EditText) findViewById(R.id.email);
        dobField = (EditText) findViewById(R.id.dob);
        signupBtn = (Button) findViewById(R.id.submit);
        dobField.setOnClickListener(view -> {
            dobField.setTextColor(Color.parseColor("black"));
            LocalDate lDate = LocalDate.now();
            new DatePickerDialog(Assignment2MainActivity.this,(view1, year, month, dayOfMonth) -> {
                String dob = (month+1)+"/"+dayOfMonth+"/"+year;
                dobField.setText(dob);
            },lDate.getYear(), lDate.getMonthValue()-1, lDate.getDayOfMonth() ).show();
        });
        signupBtn.setOnClickListener(view->{
            String name = nameField.getText().toString();
            String username = usernameField.getText().toString();
            String email = emailField.getText().toString();
            String dob = dobField.getText().toString();
            if(dob != null && !dob.isEmpty()){
                String[] dobArr = dob.split("/");
                LocalDate dateOfBirth = LocalDate.of(Integer.parseInt(dobArr[2]),Integer.parseInt(dobArr[0]), Integer.parseInt(dobArr[1]));
                if(dateOfBirth.isAfter(LocalDate.now().minusYears(18))){
                    dobField.setTextColor(Color.parseColor("red"));
                    Toast.makeText(Assignment2MainActivity.this,"You're too young!", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(this, ThankYouActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    nameField.setText("");
                    usernameField.setText("");
                    emailField.setText("");
                    dobField.setText("");
                }
            }
            else {
                Toast.makeText(Assignment2MainActivity.this,"Please enter your date of birth", Toast.LENGTH_LONG).show();
            }
        });
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, MainFragment.newInstance())
//                    .commitNow();
//        }
    }
}