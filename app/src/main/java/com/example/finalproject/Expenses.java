package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Expenses extends AppCompatActivity {

    EditText eName, eCategory, eAmount;

    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "myPref";
    private static final String KEY_NAME = "name";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_AMOUNT = "amount";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        eName = findViewById(R.id.editTextTextPersonName20);
        eCategory = findViewById(R.id.editTextTextPersonName21);
        eAmount = findViewById(R.id.editTextTextPersonName22);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

    }

    public void addExpense(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_NAME, eName.getText().toString());
        editor.putString(KEY_CATEGORY, eCategory.getText().toString());
        editor.putString(KEY_AMOUNT, eAmount.getText().toString());

        editor.apply();

        Toast.makeText(Expenses.this, "Added Expense!", Toast.LENGTH_LONG).show();
        Log.i("SHARED PREFERENCES", "Expense added to SharedPreferences");
    }

    public void cancel(View view) {
        // return to MainActivity menu
        startActivity(new Intent(Expenses.this, MainActivity.class));
    }
}