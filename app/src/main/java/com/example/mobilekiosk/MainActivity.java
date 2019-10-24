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
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent userIDintent = getIntent();
        userID = userIDintent.getStringExtra("userID");

        map = (ImageView)findViewById(R.id.map);
        btn_bobburger = (ImageButton)findViewById(R.id.btn_bobburger);

        btn_bobburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Order_Menu.class);
                intent.putExtra("userID",userID);
                startActivity(intent);
            }
        });
    }
}
