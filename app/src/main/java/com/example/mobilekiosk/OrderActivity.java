package com.example.mobilekiosk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class OrderActivity extends AppCompatActivity {
    private Context mContext;
    private TabLayout mTabLayout;
    private TextView numValue;
    private TextView priceValue;
    private ViewPager vp;

    private LinearLayout ll;

    private int selected_count = 0;
    private int selected_price = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mContext = getApplicationContext();
        mTabLayout = (TabLayout) findViewById(R.id.layout_tab);
        mTabLayout.addTab(mTabLayout.newTab().setText("신메뉴"));
        mTabLayout.addTab(mTabLayout.newTab().setText("단품"));
        mTabLayout.addTab(mTabLayout.newTab().setText("세트"));
        mTabLayout.addTab(mTabLayout.newTab().setText("사이드"));
        mTabLayout.addTab(mTabLayout.newTab().setText("음료수"));

        ll = findViewById(R.id.ll);

        numValue = (TextView) findViewById(R.id.numValue);
        priceValue = (TextView) findViewById(R.id.priceValue);

        vp = (ViewPager) findViewById(R.id.vp);
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        vp.setAdapter(pagerAdapter);
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        // Set TabSelectedListener
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    public void changeValue(int price) {
        selected_count++;
        selected_price += price;
        numValue.setText(selected_count+"");
        priceValue.setText(selected_price+"");
    }

    public void addToCart(String menu, String price) {
        //추후에 버튼도 추가
        ScrollView view = findViewById(R.id.orderedItem);
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT
        );
        TextView tv = new TextView(getApplicationContext());
        tv.setLayoutParams(lparams);
        tv.setText(selected_count + " " + menu + " " + price + "원");
        this.ll.addView(tv);
    }
}
