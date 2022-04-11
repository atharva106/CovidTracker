package com.example.covidtracker.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.covidtracker.R;
import com.example.covidtracker.dto.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUp extends AppCompatActivity {
    private EditText firstNameField;
    private EditText lastNameField;
    private EditText usernameField;
    private EditText passwordField;
    private Button signupBtn;
    private TextView message;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstNameField = (EditText) findViewById(R.id.etFirstName);
        lastNameField = (EditText) findViewById(R.id.etLastName);
        usernameField = (EditText) findViewById(R.id.etUserId);
        passwordField = (EditText) findViewById(R.id.etPass);
        signupBtn = (Button) findViewById(R.id.btnFinishSignUp);
        message = (TextView)findViewById(R.id.displayMessageSignup);

        db = FirebaseFirestore.getInstance();

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup(firstNameField.getText().toString(), lastNameField.getText().toString(), usernameField.getText().toString(), passwordField.getText().toString());
            }
        });
    }

    private void signup(String firstname, String lastname, String username, String password){
        if (firstname.equals("") || lastname.equals("") || username.equals("") || password.equals("")){
            message.setText("Fill all the fields above!");
        }
        else{
            db.collection("base").document(username)
            .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot doc = task.getResult();

                        if (doc.exists()){
                            message.setText("Username already exists, try another!");
                        }
                        else{
                            message.setText("");

                            User user = new User(firstname + " " + lastname, username, password);

                            db.collection("base").document(username).set(user);

                            Intent lp = new Intent(SignUp.this, LandingPage.class);
                            lp.putExtra("UserObject", user);
                            startActivity(lp);
                        }
                    }
                }
            });
        }
    }
}