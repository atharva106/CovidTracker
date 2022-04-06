package com.example.covidtracker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.covidtracker.R;

public class SignUp extends AppCompatActivity {
    private EditText firstNameField;
    private EditText lastNameField;
    private EditText usernameField;
    private EditText passwordField;
    private Button signupBtn;
    private TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstNameField = (EditText) findViewById(R.id.etFirstName);
        lastNameField = (EditText) findViewById(R.id.etLastName);
        usernameField = (EditText) findViewById(R.id.etUserId);
        passwordField = (EditText) findViewById(R.id.etPass);
        signupBtn = (Button) findViewById(R.id.btnFinishSignUp);
        errorMessage = (TextView)findViewById(R.id.displayErrorSignup);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup(firstNameField.getText().toString(), lastNameField.getText().toString(), usernameField.getText().toString(), passwordField.getText().toString());
            }
        });
    }

    private void signup(String firstname, String lastname, String username, String password){
        if (firstname.equals("") || lastname.equals("") || username.equals("") || password.equals("")){
            errorMessage.setText("Fill all the fields above!");
        }
        else{
            // checkIfUsernameExists(); if True error else log data in database
        }
    }
}