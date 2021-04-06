package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

        DatabaseHandlerExpense db = new DatabaseHandlerExpense(this);
        List<Expense> expenses = db.getAllExpenses();
        float cat1 = 0;
        float cat2 = 0;
        float cat3 = 0;
        float cat4 = 0;
        for (Expense cn : expenses) {
            if (cn.getCategory().matches("Food")){
                cat1 += Float.parseFloat(cn.getAmount());
                Log.d("Cat: ", "Cat 1 added");
            }
            if (cn.getCategory().matches("Home")){
                cat2 += Float.parseFloat(cn.getAmount());
                Log.d("Cat: ", "Cat 2 added");
            }
            if (cn.getCategory().matches("Car")){
                cat3 += Float.parseFloat(cn.getAmount());
                Log.d("Cat: ", "Cat 3 added");
            }
            if (cn.getCategory().matches("Others")){
                cat4 += Float.parseFloat(cn.getAmount());
                Log.d("Cat: ", "Cat 4 added");
            }
        }

        TextView editText1 = (TextView) findViewById(R.id.summaryCat1);
        TextView editText2 = (TextView) findViewById(R.id.summaryCat2);
        TextView editText3 = (TextView) findViewById(R.id.summaryCat3);
        TextView editText4 = (TextView) findViewById(R.id.summaryCat4);
        editText1.setText(Float.toString(cat1));
        editText2.setText(Float.toString(cat2));
        editText3.setText(Float.toString(cat3));
        editText4.setText(Float.toString(cat4));

        //calculate today's, this week's and this month's expenses
        float today = 0;
        float month = 0;
        float year = 0;

        SimpleDateFormat dateFormat;
        String date;

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = dateFormat.format(new Date());

        String d = date.substring(0,2);
        String m = date.substring(3,5);
        String y = date.substring(6,10);
        Log.d("EToday: ", d);
        Log.d("EMonth: ", m);
        Log.d("EYear: ", y);
        expenses = db.getAllExpenses();

        String ed;
        String em;
        String ey;
        for (Expense cn : expenses) {
            if (!cn.getDate().matches("") && cn.getDate().matches("[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]")){
            ed = cn.getDate().substring(0,2);
            em = cn.getDate().substring(3,5);
            ey = cn.getDate().substring(6,10);
            Log.d("Today: ", ed);
            Log.d("Month: ", em);
            Log.d("Year: ", ey);
            if (d.matches(ed) && m.matches(em) && y.matches(ey)){
                today += Float.parseFloat(cn.getAmount());
                Log.d("Today: ", "Today added");
            }

            if (m.matches(em) && y.matches(ey)){
                month += Float.parseFloat(cn.getAmount());
                Log.d("Month: ", "Month added");
            }

            if (y.matches(ey)){
                year += Float.parseFloat(cn.getAmount());
                Log.d("Year: ", "Year added");
            }
            }
        }

        editText1 = (TextView) findViewById(R.id.todayExpenses);
        editText2 = (TextView) findViewById(R.id.monthExpenses);
        editText3 = (TextView) findViewById(R.id.yearExpenses);
        editText1.setText(Float.toString(today));
        editText2.setText(Float.toString(month));
        editText3.setText(Float.toString(year));

        DatabaseHandlerAlert db1 = new DatabaseHandlerAlert(this);
        List<Alerts> contacts = db1.getAllAlerts();
        for (Alerts cn : contacts) {
            if (cn.getCategory().matches("Food")){
                if (Float.parseFloat(cn.getAmount()) <= cat1){
                    View someView = findViewById(R.id.divider1);
                    someView.setBackgroundColor(ContextCompat.getColor(this, R.color.design_default_color_error));
                }
            }

            if (cn.getCategory().matches("Home")){
                if (Float.parseFloat(cn.getAmount()) <= cat2){
                    View someView = findViewById(R.id.divider2);
                    someView.setBackgroundColor(ContextCompat.getColor(this, R.color.design_default_color_error));
                }
            }

            if (cn.getCategory().matches("Car")){
                if (Float.parseFloat(cn.getAmount()) <= cat3){
                    View someView = findViewById(R.id.divider3);
                    someView.setBackgroundColor(ContextCompat.getColor(this, R.color.design_default_color_error));
                }
            }

            if (cn.getCategory().matches("Others")){
                if (Float.parseFloat(cn.getAmount()) <= cat4){
                    View someView = findViewById(R.id.divider4);
                    someView.setBackgroundColor(ContextCompat.getColor(this, R.color.design_default_color_error));
                }
            }
        }

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


    public void signOutFireBase(View view) {
        FirebaseAuth.getInstance().signOut();
        Log.i("MESSAGE", "LOGGING OUT");
        startActivity(new Intent(MainActivity.this, Login.class));
    }

    public void refreshSummary(View view){
        DatabaseHandlerExpense db = new DatabaseHandlerExpense(this);
        List<Expense> expenses = db.getAllExpenses();
        float cat1 = 0;
        float cat2 = 0;
        float cat3 = 0;
        float cat4 = 0;
        for (Expense cn : expenses) {
            if (cn.getCategory().matches("Food")){
                cat1 += Float.parseFloat(cn.getAmount());
                Log.d("Cat: ", "Cat 1 added");
            }
            if (cn.getCategory().matches("Home")){
                cat2 += Float.parseFloat(cn.getAmount());
                Log.d("Cat: ", "Cat 2 added");
            }
            if (cn.getCategory().matches("Car")){
                cat3 += Float.parseFloat(cn.getAmount());
                Log.d("Cat: ", "Cat 3 added");
            }
            if (cn.getCategory().matches("Others")){
                cat4 += Float.parseFloat(cn.getAmount());
                Log.d("Cat: ", "Cat 4 added");
            }
        }

        TextView editText1 = (TextView) findViewById(R.id.summaryCat1);
        TextView editText2 = (TextView) findViewById(R.id.summaryCat2);
        TextView editText3 = (TextView) findViewById(R.id.summaryCat3);
        TextView editText4 = (TextView) findViewById(R.id.summaryCat4);
        editText1.setText(Float.toString(cat1));
        editText2.setText(Float.toString(cat2));
        editText3.setText(Float.toString(cat3));
        editText4.setText(Float.toString(cat4));

        //calculate today's, this week's and this month's expenses
        float today = 0;
        float month = 0;
        float year = 0;

        SimpleDateFormat dateFormat;
        String date;

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = dateFormat.format(new Date());

        String d = date.substring(0,2);
        String m = date.substring(3,5);
        String y = date.substring(6,10);
        Log.d("EToday: ", d);
        Log.d("EMonth: ", m);
        Log.d("EYear: ", y);
        expenses = db.getAllExpenses();

        String ed;
        String em;
        String ey;
        for (Expense cn : expenses) {
            if (!cn.getDate().matches("") && cn.getDate().matches("[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]")){
                ed = cn.getDate().substring(0,2);
                em = cn.getDate().substring(3,5);
                ey = cn.getDate().substring(6,10);
                Log.d("Today: ", ed);
                Log.d("Month: ", em);
                Log.d("Year: ", ey);
                if (d.matches(ed) && m.matches(em) && y.matches(ey)){
                    today += Float.parseFloat(cn.getAmount());
                    Log.d("Today: ", "Today added");
                }

                if (m.matches(em) && y.matches(ey)){
                    month += Float.parseFloat(cn.getAmount());
                    Log.d("Month: ", "Month added");
                }

                if (y.matches(ey)){
                    year += Float.parseFloat(cn.getAmount());
                    Log.d("Year: ", "Year added");
                }
            }
        }

        editText1 = (TextView) findViewById(R.id.todayExpenses);
        editText2 = (TextView) findViewById(R.id.monthExpenses);
        editText3 = (TextView) findViewById(R.id.yearExpenses);
        editText1.setText(Float.toString(today));
        editText2.setText(Float.toString(month));
        editText3.setText(Float.toString(year));
        DatabaseHandlerAlert db1 = new DatabaseHandlerAlert(this);
        List<Alerts> contacts = db1.getAllAlerts();
        for (Alerts cn : contacts) {
            if (cn.getCategory().matches("Food")){
                if (Float.parseFloat(cn.getAmount()) <= cat1){
                    View someView = findViewById(R.id.divider1);
                    someView.setBackgroundColor(ContextCompat.getColor(this, R.color.design_default_color_error));
                }
            }

            if (cn.getCategory().matches("Home")){
                if (Float.parseFloat(cn.getAmount()) <= cat2){
                    View someView = findViewById(R.id.divider2);
                    someView.setBackgroundColor(ContextCompat.getColor(this, R.color.design_default_color_error));
                }
            }

            if (cn.getCategory().matches("Car")){
                if (Float.parseFloat(cn.getAmount()) <= cat3){
                    View someView = findViewById(R.id.divider3);
                    someView.setBackgroundColor(ContextCompat.getColor(this, R.color.design_default_color_error));
                }
            }

            if (cn.getCategory().matches("Others")){
                if (Float.parseFloat(cn.getAmount()) <= cat4){
                    View someView = findViewById(R.id.divider4);
                    someView.setBackgroundColor(ContextCompat.getColor(this, R.color.design_default_color_error));
                }
            }
        }
    }


    }