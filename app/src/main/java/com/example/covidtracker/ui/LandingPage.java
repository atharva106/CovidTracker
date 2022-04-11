package com.example.covidtracker.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.covidtracker.R;
import com.example.covidtracker.dto.User;

public class LandingPage extends AppCompatActivity {
    private TextView welcomeText;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        welcomeText = (TextView) findViewById(R.id.welcome);
        user = (User) getIntent().getSerializableExtra("UserObject");

        welcomeText.setText("Welcome " + user.getName());
    }
}