package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
//Class to create a way to handle the Expenses' database.

public class DatabaseHandlerExpense extends SQLiteOpenHelper {
    //Database basic info, including the database name, version, table name, and the columns in the database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Expenses";
    private static final String TABLE_EXPENSES = "expenses";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_DATE = "date";
    private static final String KEY_AMOUNT = "amount";

    public DatabaseHandlerExpense(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EXPENSES_TABLE = "CREATE TABLE " + TABLE_EXPENSES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_CATEGORY + " TEXT," + KEY_DATE + " TEXT," + KEY_AMOUNT + " TEXT" + ")";
        db.execSQL(CREATE_EXPENSES_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSES);

        // Create tables again
        onCreate(db);
    }

    // code to add the new expense
    void addExpense(Expense expense) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, expense.getName()); // Expense Name
        values.put(KEY_CATEGORY, expense.getCategory()); // Expense Category
        values.put(KEY_DATE, expense.getDate()); // Expense Date
        values.put(KEY_AMOUNT, expense.getAmount()); // Expense Amount
        // Inserting Row
        db.insert(TABLE_EXPENSES, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single expense
    Expense getExpense(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EXPENSES, new String[] { KEY_ID,
                        KEY_NAME, KEY_CATEGORY, KEY_DATE, KEY_AMOUNT }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Expense expense = new Expense(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        // return expense
        return expense;
    }

    // code to get all expenses in a list view
    public List<Expense> getAllExpenses() {
        List<Expense> expenseList = new ArrayList<Expense>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EXPENSES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Expense expense = new Expense();
                expense.setID(Integer.parseInt(cursor.getString(0)));
                expense.setName(cursor.getString(1));
                expense.setCategory(cursor.getString(2));
                expense.setDate(cursor.getString(3));
                expense.setAmount(cursor.getString(4));
                // Adding expense to list
                expenseList.add(expense);
            } while (cursor.moveToNext());
        }

        // return expense list
        return expenseList;
    }

    // code to update the single expense
    public int updateExpense(Expense expense) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, expense.getName());
        values.put(KEY_CATEGORY, expense.getCategory());
        values.put(KEY_DATE, expense.getDate());
        values.put(KEY_AMOUNT, expense.getAmount());

        // updating row
        return db.update(TABLE_EXPENSES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(expense.getID()) });
    }

    // Deleting single expense
    public void deleteExpense(Expense expense) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EXPENSES, KEY_ID + " = ?",
                new String[] { String.valueOf(expense.getID()) });
        db.close();
    }

    // Getting expenses Count
    public int getExpenseCount() {
        String countQuery = "SELECT  * FROM " + TABLE_EXPENSES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


}
