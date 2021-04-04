package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandlerAlert extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Alerts";
    private static final String TABLE_ALERTS = "alerts";
    private static final String KEY_ID = "id";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_AMOUNT = "amount";

    public DatabaseHandlerAlert(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EXPENSES_TABLE = "CREATE TABLE " + TABLE_ALERTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CATEGORY + " TEXT,"
                + KEY_AMOUNT + " TEXT" + ")";
        db.execSQL(CREATE_EXPENSES_TABLE);


    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALERTS);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    void addAlert(Alerts alert) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORY, alert.getCategory()); // Contact Category
        values.put(KEY_AMOUNT, alert.getAmount()); // Contact Amount
        // Inserting Row
        db.insert(TABLE_ALERTS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
    Alerts getAlert(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ALERTS, new String[] { KEY_ID,
                        KEY_CATEGORY, KEY_AMOUNT }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Alerts alert = new Alerts(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return alert
        return alert;
    }

    // code to get all contacts in a list view
    public List<Alerts> getAllAlerts() {
        List<Alerts> alertsList = new ArrayList<Alerts>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ALERTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Alerts alert = new Alerts();
                alert.setID(Integer.parseInt(cursor.getString(0)));
                alert.setCategory(cursor.getString(1));
                alert.setAmount(cursor.getString(2));
                // Adding contact to list
                alertsList.add(alert);
            } while (cursor.moveToNext());
        }

        // return contact list
        return alertsList;
    }

    // code to update the single contact
    public int updateAlert(Alerts alert) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORY, alert.getCategory());
        values.put(KEY_AMOUNT, alert.getAmount());

        // updating row
        return db.update(TABLE_ALERTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(alert.getID()) });
    }

    // Deleting single contact
    public void deleteAlert(Alerts alert) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ALERTS, KEY_ID + " = ?",
                new String[] { String.valueOf(alert.getID()) });
        db.close();
    }

    // Getting contacts Count
    public int getAlertCount() {
        String countQuery = "SELECT  * FROM " + TABLE_ALERTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


}


