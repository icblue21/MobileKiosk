package com.example.mobilekiosk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView map;
    ImageButton btn_bobburger;
    ImageButton btn_hojupmong;
    ImageButton btn_jongro;
    boolean bobburgerClicked = false;
    boolean hojupmongClicked = false;
    boolean jongroClicked = false;
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
                if(!bobburgerClicked) {
                    bobburgerClicked = true;
                    jongroClicked = false;
                    hojupmongClicked = false;

                    //버거 사이즈 조절.
                    android.view.ViewGroup.LayoutParams params = btn_bobburger.getLayoutParams();
                    params.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 105, getResources().getDisplayMetrics());
                    params.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics());
                    btn_bobburger.setLayoutParams(params);
                    btn_bobburger.setScaleType(ImageView.ScaleType.FIT_CENTER);

                    //호접몽 사이즈 조절.
                    android.view.ViewGroup.LayoutParams params2 = btn_hojupmong.getLayoutParams();
                    params2.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 65, getResources().getDisplayMetrics());
                    params2.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 75, getResources().getDisplayMetrics());
                    btn_hojupmong.setLayoutParams(params2);
                    btn_hojupmong.setScaleType(ImageView.ScaleType.FIT_CENTER);

                    //종로 사이즈 조절.
                    android.view.ViewGroup.LayoutParams params3 = btn_jongro.getLayoutParams();
                    params3.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 65, getResources().getDisplayMetrics());
                    params3.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 75, getResources().getDisplayMetrics());
                    btn_jongro.setLayoutParams(params3);
                    btn_jongro.setScaleType(ImageView.ScaleType.FIT_CENTER);

                } else {
                    Intent intent = new Intent(MainActivity.this,Order_Menu.class);
                    intent.putExtra("userID",userID);
                    startActivity(intent);
                }

            }
        });
        btn_hojupmong.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(!hojupmongClicked) {
                    bobburgerClicked = false;
                    jongroClicked = false;
                    hojupmongClicked = true;

                    //버거 사이즈 조절.
                    android.view.ViewGroup.LayoutParams params = btn_bobburger.getLayoutParams();
                    params.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 65, getResources().getDisplayMetrics());
                    params.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 75, getResources().getDisplayMetrics());
                    btn_bobburger.setLayoutParams(params);
                    btn_bobburger.setScaleType(ImageView.ScaleType.FIT_CENTER);

                    //호접몽 사이즈 조절.
                    android.view.ViewGroup.LayoutParams params2 = btn_hojupmong.getLayoutParams();
                    params2.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 105, getResources().getDisplayMetrics());
                    params2.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics());
                    btn_hojupmong.setLayoutParams(params2);
                    btn_hojupmong.setScaleType(ImageView.ScaleType.FIT_CENTER);

                    //종로 사이즈 조절.
                    android.view.ViewGroup.LayoutParams params3 = btn_jongro.getLayoutParams();
                    params3.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 65, getResources().getDisplayMetrics());
                    params3.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 75, getResources().getDisplayMetrics());
                    btn_jongro.setLayoutParams(params3);
                    btn_jongro.setScaleType(ImageView.ScaleType.FIT_CENTER);

                } else {
                    Intent intent = new Intent(MainActivity.this,Order_Hojupmong.class);
                    intent.putExtra("userID",userID);
                    startActivity(intent);
                }
            }
        });
        btn_jongro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(!jongroClicked) {
                    bobburgerClicked = false;
                    jongroClicked = true;
                    hojupmongClicked = false;

                    //버거 사이즈 조절.
                    android.view.ViewGroup.LayoutParams params = btn_bobburger.getLayoutParams();
                    params.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 65, getResources().getDisplayMetrics());
                    params.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 75, getResources().getDisplayMetrics());
                    btn_bobburger.setLayoutParams(params);
                    btn_bobburger.setScaleType(ImageView.ScaleType.FIT_CENTER);

                    //호접몽 사이즈 조절.
                    android.view.ViewGroup.LayoutParams params2 = btn_hojupmong.getLayoutParams();
                    params2.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 65, getResources().getDisplayMetrics());
                    params2.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 75, getResources().getDisplayMetrics());
                    btn_hojupmong.setLayoutParams(params2);
                    btn_hojupmong.setScaleType(ImageView.ScaleType.FIT_CENTER);

                    //종로 사이즈 조절.
                    android.view.ViewGroup.LayoutParams params3 = btn_jongro.getLayoutParams();
                    params3.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 105, getResources().getDisplayMetrics());
                    params3.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics());
                    btn_jongro.setLayoutParams(params3);
                    btn_jongro.setScaleType(ImageView.ScaleType.FIT_CENTER);

                } else {
                    Intent intent = new Intent(MainActivity.this,Order_Jongro.class);
                    intent.putExtra("userID",userID);
                    startActivity(intent);
                }
            }
        });
    }
    public void onBackPressed(){

        Intent returnintent = new Intent(this,OrderChooseActivity.class);
        startActivity(returnintent);
        super.onBackPressed();
    }
}
