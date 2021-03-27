package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity  {

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /*
        // remove comment to initiate registration and login activities
        // don't forget to sign out

        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() == null) {
            startActivity(new Intent(MainActivity.this, Login.class));
        }
        */

    }

    public void addExpense(View view) {
        Intent intent = new Intent(this, Expenses.class);
        Log.i("MESSAGE", "Adding Expense");
        startActivity(intent);
    }

    public void addAlert(View view) {
        Intent intent = new Intent(this, Alert.class);
        Log.i("MESSAGE", "Adding Alert");
        startActivity(intent);
    }

    public void getSummary(View view) {
        Intent intent = new Intent(this, Summary.class);
        Log.i("MESSAGE", "Getting Summary");
        startActivity(intent);
    }

    public void signOutFireBase(View view) {
        FirebaseAuth.getInstance().signOut();
        Log.i("MESSAGE", "LOGGING OUT");
        startActivity(new Intent(MainActivity.this, Login.class));
    }
    }