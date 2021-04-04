package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;



import java.util.List;

public class Expenses extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        Spinner spinner = findViewById(R.id.expenseCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


    }

    public void addExpenses(View view){
        DatabaseHandlerExpense db = new DatabaseHandlerExpense(this);
        EditText name = (EditText) findViewById(R.id.expenseName);
        Spinner category = (Spinner) findViewById(R.id.expenseCategory);
        EditText date = (EditText) findViewById(R.id.expenseDate);
        EditText amount = (EditText) findViewById(R.id.expenseAmount);
        String n = name.getText().toString();
        String c = category.getSelectedItem().toString();
        String d = date.getText().toString();
        String a = amount.getText().toString();
        if (n.matches("") || c.matches("") || d.matches("") || a.matches("")  ){
            Toast.makeText(this,"Fill all the information to add an expense", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            db.addExpense(new Expense(n, c, d, a));

            // Reading all contacts
            Log.d("Reading: ", "Reading all contacts..");
            List<Expense> contacts = db.getAllExpenses();

            for (Expense cn : contacts) {
                String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Category: " +
                        cn.getCategory() + ",Date: " + cn.getDate() + "Amount: " + cn.getAmount();
                // Writing Contacts to log
                Log.d("Name: ", log);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       // String text = parent.getItemAtPosition(position).toString();
       // Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}