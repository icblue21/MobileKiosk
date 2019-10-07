package com.example.mobilekiosk;

public class MenuData {

    private String Name;
    private int Price;
    private int Quntity;
    private int SetCode;
    MenuData(String name, int price, int quntity){
        Name = name;
        Price = price;
        Quntity = quntity;

    }
    MenuData(){


    }
    public String GetName(){
        return Name;
    }
    public int GetPrice(){

        return Price;
    }

    public int GetQuntity(){
        return Quntity;
    }
    public void Set(int data){
        SetCode = data;
    }
    public void SetQuntity(int data){
        if(data !=0) {
            Quntity = data;
        }
    }
    public int GetFee(){
        return Price*Quntity;
    }

}
