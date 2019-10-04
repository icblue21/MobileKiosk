package com.example.mobilekiosk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class OrderChooseActivity extends AppCompatActivity {

    ImageView orderlist;
    ImageView order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_choose);

        orderlist = (ImageView)findViewById(R.id.orderlist) ;
        order = (ImageView)findViewById(R.id.order);

        Button orderlistButton = (Button) findViewById(R.id.orderlistButton);
        Button orderButton = (Button) findViewById(R.id.orderButton);

        orderlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orderlistIntent = new Intent(OrderChooseActivity.this, OrderlistActivity.class);
                OrderChooseActivity.this.startActivity(orderlistIntent);
            }
        });

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orderIntent = new Intent(OrderChooseActivity.this, OrderActivity.class);
                OrderChooseActivity.this.startActivity(orderIntent);
            }
        });

    }
}
