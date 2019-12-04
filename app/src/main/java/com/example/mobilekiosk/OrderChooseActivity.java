package com.example.mobilekiosk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class OrderChooseActivity extends AppCompatActivity implements BusProvider.OntimeListener{

    ImageView orderlist;
    ImageView order;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_choose);

        orderlist = (ImageView)findViewById(R.id.tekeout) ;
        order = (ImageView)findViewById(R.id.cutlery);
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");


        Button orderlistButton = (Button) findViewById(R.id.orderlistButton);
        Button orderButton = (Button) findViewById(R.id.orderButton);

        orderlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orderlistIntent = new Intent(OrderChooseActivity.this, OrderlistActivity.class);

                orderlistIntent.putExtra("userID",userID);

                OrderChooseActivity.this.startActivity(orderlistIntent);
            }
        });

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orderIntent = new Intent(OrderChooseActivity.this, MainActivity.class);
                orderIntent.putExtra("userID",userID);

                OrderChooseActivity.this.startActivity(orderIntent);
            }
        });

    }
    public void ontimePickerset(String name, int price) {
        if (price < 0) {
            finish();
        }
    }

    public void onBackPressed(){
    }
}
