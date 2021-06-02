package com.example.datingapp.assignment2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.datingapp.R;

public class ThankYouActivity extends AppCompatActivity {
    private TextView thanksEditText;
    private Button backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thank_you_activity);
        String username = getIntent().getStringExtra("username");

        thanksEditText = (TextView) findViewById(R.id.thankYouTextView);
        backBtn = (Button) findViewById(R.id.backbtn);
        thanksEditText.setText("Thanks for Signing Up "+username+" !");
        backBtn.setOnClickListener(view->{finish();});
    }
}