package com.example.finalproject;

public class Expense {
    int _id;
    String _name;
    String _category;
    String _date;
    String _amount;
    public Expense(){   }

    public Expense(int id, String name, String category, String date, String amount){
        this._id = id;
        this._name = name;
        this._category = category;
        this._date = date;
        this._amount = amount;
    }

    public Expense(String name, String category, String date, String amount){
        this._name = name;
        this._category = category;
        this._date = date;
        this._amount = amount;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getName(){
        return this._name;
    }

    public void setName(String name){
        this._name = name;
    }

    public String getCategory(){
        return this._category;
    }

    public void setCategory(String category){ this._category = category; }

    public String getDate(){
        return this._date;
    }

    public void setDate(String date){ this._date = date; }

    public String getAmount(){
        return this._amount;
    }

    public void setAmount(String amount){ this._amount = amount; }

}
