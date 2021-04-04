package com.example.finalproject;

public class Alerts {
    int _id;
    String _category;
    String _amount;
    public Alerts(){   }

    public Alerts(int id, String category, String amount){
        this._id = id;
        this._category = category;
        this._amount = amount;
    }

    public Alerts(String category, String amount){
        this._category = category;
        this._amount = amount;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }


    public String getCategory(){
        return this._category;
    }

    public void setCategory(String category){ this._category = category; }


    public String getAmount(){
        return this._amount;
    }

    public void setAmount(String amount){ this._amount = amount; }

}
