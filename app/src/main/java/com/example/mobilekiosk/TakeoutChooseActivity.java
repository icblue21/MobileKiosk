package com.example.mobilekiosk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TakeoutChooseActivity extends AppCompatActivity {

    Button takeoutButton;
    Button cutleryButton;
    MenuData MenuList[];
    String userID;
    String wholeInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takeout_choose);

        takeoutButton = (Button)findViewById(R.id.takeoutButton);
        cutleryButton = (Button)findViewById(R.id.cutleryButton);
        Intent intent = getIntent();
        MenuList = (MenuData[]) intent.getSerializableExtra("MenuData");
        userID = intent.getStringExtra("userID");
        wholeInfo = intent.getStringExtra("wholeInfo");

        takeoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takeoutintent = new Intent(TakeoutChooseActivity.this,PayHistory.class);
                takeoutintent.putExtra("MenuData",MenuList);
                takeoutintent.putExtra("userID",userID);
                takeoutintent.putExtra("wholeInfo",wholeInfo);
                startActivity(takeoutintent);
            }
        });

        cutleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cutleryintent = new Intent(TakeoutChooseActivity.this,PayHistory.class);
                cutleryintent.putExtra("MenuData",MenuList);
                cutleryintent.putExtra("userID",userID);
                cutleryintent.putExtra("wholeInfo",wholeInfo);
                startActivity(cutleryintent);
            }
        });


    }
}
