package com.example.mobilekiosk;


    public class OrderData {

        public String name;
        public int quantity;
        public int totalprice;

        public OrderData() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public OrderData(String name, int quantity, int price) {
            this.name = name;
            this.quantity = quantity;
            this.totalprice = price;

        }
    }

