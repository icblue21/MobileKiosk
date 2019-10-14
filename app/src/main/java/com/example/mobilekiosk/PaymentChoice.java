package com.example.mobilekiosk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PaymentChoice extends AppCompatActivity implements View.OnClickListener {

    ImageButton CreditCard;
    ImageButton Paycobutton;
    MenuData MenuList[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_choice);
        Intent intent = getIntent();
        MenuList = (MenuData[])intent.getSerializableExtra("MenuData");
        this.InitializeView();
        this.SetListener();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.PaycoButton:
                Intent intent = new Intent(this,PayHistory.class);
                intent.putExtra("MenuData",MenuList);
                startActivity(intent);

                break;


            case R.id.CreditButton:

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
}
