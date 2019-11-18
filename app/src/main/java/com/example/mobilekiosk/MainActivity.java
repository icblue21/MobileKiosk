package com.example.mobilekiosk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView map;
    ImageButton btn_bobburger;
    ImageButton btn_hojupmong;
    ImageButton btn_jongro;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent userIDintent = getIntent();
        userID = userIDintent.getStringExtra("userID");

        map = (ImageView)findViewById(R.id.cutlery);
        btn_bobburger = (ImageButton)findViewById(R.id.btn_bobburger);
        btn_hojupmong = (ImageButton)findViewById(R.id.btn_hojupmong);
        btn_jongro = (ImageButton)findViewById(R.id.btn_jongro);

        btn_bobburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Order_Menu.class);
                intent.putExtra("userID",userID);
                startActivity(intent);
            }
        });
        btn_hojupmong.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Order_Hojupmong.class);
                intent.putExtra("UserID",userID);
                startActivity(intent);
            }
        });
        btn_jongro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Order_Jongro.class);
                intent.putExtra("UserID",userID);
                startActivity(intent);
            }
        });
    }
    public void onBackPressed(){

        Intent returnintent = new Intent(this,OrderChooseActivity.class);
        startActivity(returnintent);
        super.onBackPressed();
    }
}
