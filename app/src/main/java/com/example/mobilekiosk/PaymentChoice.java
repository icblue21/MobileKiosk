package com.example.mobilekiosk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class PaymentChoice extends AppCompatActivity implements View.OnClickListener {

    ImageButton CreditCard;
    ImageButton Paycobutton;
    MenuData MenuList[];
    String StoreId;

    private BusProvider.OntimeListener ontime;
    public static ArrayList<Activity> actList = new ArrayList<Activity>();



    String wholeInfo;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_choice);
        Intent intent = getIntent();
        MenuList = (MenuData[])intent.getSerializableExtra("MenuData");
        userID = intent.getStringExtra("userID");
        wholeInfo = (String) intent.getSerializableExtra("wholeInfo");
        StoreId = intent.getStringExtra("storeid");
        this.InitializeView();
        this.SetListener();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.PaycoButton:



                break;


            case R.id.CreditButton:
                ActivityKill();
                Intent intent = new Intent(this,TakeoutChooseActivity.class);
                intent.putExtra("wholeInfo",wholeInfo);
                intent.putExtra("MenuData",MenuList);
                intent.putExtra("userID",userID);
                intent.putExtra("storeid",StoreId);
                startActivity(intent);


                break;


        }
    }

    void SetListener(){
        CreditCard.setOnClickListener(this);
        Paycobutton.setOnClickListener(this);

    }

    public void InitializeView(){


        CreditCard = (ImageButton)findViewById(R.id.CreditButton);
        Paycobutton = (ImageButton)findViewById(R.id.PaycoButton);

    }
    void ActivityKill(){


    }

}
