package com.example.covidtracker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.covidtracker.R;

public class Login extends AppCompatActivity {
    private EditText usernameField;
    private EditText passwordField;
    private Button loginBtn;
    private TextView signupBtn;
    private TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameField = (EditText) findViewById(R.id.etUsername);
        passwordField = (EditText) findViewById(R.id.etPassword);
        loginBtn = (Button) findViewById(R.id.btnLogin);
        signupBtn = (TextView) findViewById(R.id.btnSignup);
        errorMessage = (TextView)findViewById(R.id.displayErrorLogin);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(usernameField.getText().toString(), passwordField.getText().toString());
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(Login.this, SignUp.class);
                startActivity(s);
            }
        });
    }

    private void login(String username, String password){
        if (username.equals("") && password.equals("")){
            errorMessage.setText("Enter valid credentials!!");
        }
        else if (username.equals("")){
            errorMessage.setText("Enter your username!");
        }
        else if (password.equals("")){
            errorMessage.setText("Enter your password!");
        }
        else{
            // Login code

//            checkIfUsernameExists(); if True checkPassword(), else error


        }
    }
}