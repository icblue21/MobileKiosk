package com.example.mobilekiosk;

public class Store {
    String name;
    String category;
    String menuList[];
    public Store(String newname, String newcategory,String[] menuList){
        this.name = newname;
        this.category = newcategory;
        this.menuList = menuList;
    }
}
