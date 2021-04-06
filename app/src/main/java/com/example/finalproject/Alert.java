package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class Alert extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        //Create db handler to add new entries to the database or query it.
        DatabaseHandlerAlert db = new DatabaseHandlerAlert(this);
        //Create spinner object to add string values to the spinner options for the Alert UI.
        Spinner spinner = findViewById(R.id.alertCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        //Query database
        List<Alerts> contacts = db.getAllAlerts();
        //If dabase of alerts is empty, this will create default entries.
        if (contacts.isEmpty()){
            db.addAlert(new Alerts("Food","1000"));
            db.addAlert(new Alerts("Home","1000"));
            db.addAlert(new Alerts("Car","1000"));
            db.addAlert(new Alerts("Others","1000"));
        }
        //Display the current alert limits
        contacts = db.getAllAlerts();
        TextView editText1;
        for (Alerts cn : contacts) {
            String log = "Id: " + cn.getID() + " ,Category: " +
                    cn.getCategory() + "Amount: " + cn.getAmount();
            // Writing Alerts to log
            if (cn.getID() == 1){
                editText1 = (TextView) findViewById(R.id.limit0);
                editText1.setText(cn.getAmount());
            }
            if (cn.getID() == 2){
                editText1 = (TextView) findViewById(R.id.limit1);
                editText1.setText(cn.getAmount());
            }
            if (cn.getID() == 3){
                editText1 = (TextView) findViewById(R.id.limit2);
                editText1.setText(cn.getAmount());
            }
            if (cn.getID() == 4){
                editText1 = (TextView) findViewById(R.id.limit3);
                editText1.setText(cn.getAmount());
            }
            Log.d("Name: ", log);
        }
    }

    public void setLimit(View view){
        //Create db handler to add new entries to the database or query it.
        DatabaseHandlerAlert db = new DatabaseHandlerAlert(this);
        //Get input values from user
        Spinner category = (Spinner) findViewById(R.id.alertCategory);
        EditText amount = (EditText) findViewById(R.id.alertAmount);
        String c = category.getSelectedItem().toString();
        String a = amount.getText().toString();
        //Query database
        List<Alerts> contacts = db.getAllAlerts();
        // go through all 4 categories and assign them the new amount value if there was a change.
        for (Alerts cn : contacts) {
            if (c.matches(cn.getCategory())){
                cn.setAmount(a);
                db.updateAlert(cn);
                refreshLimit(view);
            }
        }
    }

    public void refreshLimit(View view){
        //Create db handler to add new entries to the database or query it.
        DatabaseHandlerAlert db = new DatabaseHandlerAlert(this);
        //Go through the database entries and update the amount value on the GUI
        List<Alerts> contacts = db.getAllAlerts();
        TextView editText1;
        for (Alerts cn : contacts) {
            String log = "Id: " + cn.getID() + " ,Category: " +
                    cn.getCategory() + "Amount: " + cn.getAmount();
            // Writing Alerts to log
            if (cn.getID() == 1){
                editText1 = (TextView) findViewById(R.id.limit0);
                editText1.setText(cn.getAmount());
            }
            if (cn.getID() == 2){
                editText1 = (TextView) findViewById(R.id.limit1);
                editText1.setText(cn.getAmount());
            }
            if (cn.getID() == 3){
                editText1 = (TextView) findViewById(R.id.limit2);
                editText1.setText(cn.getAmount());
            }
            if (cn.getID() == 4){
                editText1 = (TextView) findViewById(R.id.limit3);
                editText1.setText(cn.getAmount());
            }
            Log.d("Name: ", log);
        }
    }
    //Method for the spinner when an item is selected.
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }
    //Method for the spinner when an item is not selected.
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
