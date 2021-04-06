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
        //Create spinner object to add string values to the spinner options for the Alert UI.
        Spinner spinner = findViewById(R.id.expenseCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


    }

    public void addExpenses(View view){
        //Create db handler to add new entries to the database or query it.
        DatabaseHandlerExpense db = new DatabaseHandlerExpense(this);
        //Get input values from user
        EditText name = (EditText) findViewById(R.id.expenseName);
        Spinner category = (Spinner) findViewById(R.id.expenseCategory);
        EditText date = (EditText) findViewById(R.id.expenseDate);
        EditText amount = (EditText) findViewById(R.id.expenseAmount);
        String n = name.getText().toString();
        String c = category.getSelectedItem().toString();
        String d = date.getText().toString();
        String a = amount.getText().toString();
        //Verify that none of the fields is empty, if any is empty then show a toast.
        if (n.matches("") || c.matches("") || d.matches("") || a.matches("")  ){
            Toast.makeText(this,"Fill all the information to add an expense", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            db.addExpense(new Expense(n, c, d, a));

            // Reading all expenses
            Log.d("Reading: ", "Reading all contacts..");
            List<Expense> contacts = db.getAllExpenses();

            for (Expense cn : contacts) {
                String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Category: " +
                        cn.getCategory() + ",Date: " + cn.getDate() + "Amount: " + cn.getAmount();
                // Writing expenses to log
                Log.d("Name: ", log);
            }
        }
    }
    //Method for the spinner when an item is selected.
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       // String text = parent.getItemAtPosition(position).toString();
       // Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }
    //Method for the spinner when an item is not selected.
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
