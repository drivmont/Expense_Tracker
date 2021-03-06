package com.example.finalproject;
//This class allows the database handler to create new Expenses and modify them.
public class Expense {
    //Define variables that every expense will have inside the database
    int _id;
    String _name;
    String _category;
    String _date;
    String _amount;
    public Expense(){   }

    public Expense(int id, String name, String category, String date, String amount){
        //Initialize an expense        
        this._id = id;
        this._name = name;
        this._category = category;
        this._date = date;
        this._amount = amount;
    }

    public Expense(String name, String category, String date, String amount){
        //Only change category, date and amount. It doesn't include ID since it is autogenerated.
        this._name = name;
        this._category = category;
        this._date = date;
        this._amount = amount;
    }

    public int getID(){
        //Method to get the ID value
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getName(){
        return this._name;
    }

    public void setName(String name){
        //Method to change the ID value
        this._name = name;
    }

    public String getCategory(){
        //Method to get the category value
        return this._category;
    }
    //Method to change the category value
    public void setCategory(String category){ this._category = category; }

    public String getDate(){
    //Method to get the date value
        return this._date;
    }
    //Method to change the date value.
    public void setDate(String date){ this._date = date; }

    public String getAmount(){
        //Method to get the amount value for an alert
        return this._amount;
    }
    //Method to change the amount value of an alert
    public void setAmount(String amount){ this._amount = amount; }

}
