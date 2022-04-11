package com.example.covidtracker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.covidtracker.R;
import com.example.covidtracker.dto.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;

public class Login extends AppCompatActivity {
    private EditText usernameField;
    private EditText passwordField;
    private Button loginBtn;
    private TextView signupBtn;
    private TextView errorMessage;
    private FirebaseFirestore db;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameField = (EditText) findViewById(R.id.etUsername);
        passwordField = (EditText) findViewById(R.id.etPassword);
        loginBtn = (Button) findViewById(R.id.btnLogin);
        signupBtn = (TextView) findViewById(R.id.btnSignup);
        errorMessage = (TextView)findViewById(R.id.displayErrorLogin);

        db = FirebaseFirestore.getInstance();
        user = new User();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check(usernameField.getText().toString(), passwordField.getText().toString())){
                    user.setUsername(usernameField.getText().toString());
                    user.setPassword(passwordField.getText().toString());

                    db.collection("base").document(user.getUsername())
                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()){
                                DocumentSnapshot doc = task.getResult();

                                if (doc.exists()){
                                    if (user.getPassword().equals(doc.get("password").toString())){
                                        errorMessage.setText("");

                                        user.setName(doc.get("name").toString());

                                        Intent lp = new Intent(Login.this, LandingPage.class);
                                        lp.putExtra("UserObject", user);
                                        startActivity(lp);
                                    }
                                    else{
                                        errorMessage.setText("Wrong password, Try again!");
                                    }
                                }
                                else{
                                    errorMessage.setText("User not found, try signing up!");
                                }
                            }
                        }
                    });
                }
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

    public boolean check(String username, String password){
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
            return true;
        }

        return false;
    }
}