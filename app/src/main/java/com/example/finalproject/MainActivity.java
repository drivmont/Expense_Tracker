package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


}