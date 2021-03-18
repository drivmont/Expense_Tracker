package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Activity registers user
 *
 * Register activity collects user input full name, email and password and verifies input is
 * correct and meets the standard requirements.
 *
 * @author    Dustin Lodge
 */
public class Register extends AppCompatActivity {

    EditText rFullName, rEmail, rPassword;
    Button rButtonRegister;
    TextView rCreateLogin;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rFullName = findViewById(R.id.fullName);
        rEmail = findViewById(R.id.email);
        rPassword = findViewById(R.id.password);

        rButtonRegister = findViewById(R.id.buttonRegister);
        rCreateLogin = findViewById(R.id.createLogin);
        progressBar = findViewById(R.id.progressBar);

        fAuth = FirebaseAuth.getInstance();

        //button stores user credentials to FireBase authentication
        rButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //collects and verifies correct user input
                String fullName = rFullName.getText().toString().trim();
                String email = rEmail.getText().toString().trim();
                String password = rPassword.getText().toString().trim();

                if(fullName.isEmpty()) {
                    rFullName.setError("Full name is required!");
                    rFullName.requestFocus();
                    return;
                }

                if(email.isEmpty()) {
                    rEmail.setError("Email is required!");
                    rEmail.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    rEmail.setError("Please provide email format name@server.com!");
                    rEmail.requestFocus();
                    return;
                }

                if(password.isEmpty()) {
                    rPassword.setError("Password is required!");
                    rPassword.requestFocus();
                    return;
                }

                if(password.length() < 8) {
                    rPassword.setError("Password must be 8 characters or more!");
                    rPassword.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // registers user in FireBase
                fAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(Register.this,
                                            "User created successfully! Login now!", Toast.LENGTH_LONG).show();
                                    Log.i("MESSAGE", "Registered, heading to login");
                                    startActivity(new Intent(getApplicationContext(), Login.class));

                                } else {
                                    Toast.makeText(Register.this,
                                            "Failed to register! Please try again!", Toast.LENGTH_LONG).show();
                                    Log.i("MESSAGE", "Registration unsuccessful");
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        });

            }
        });
        // sends user to Login activity
        rCreateLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MESSAGE", "Heading to login");
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }
}