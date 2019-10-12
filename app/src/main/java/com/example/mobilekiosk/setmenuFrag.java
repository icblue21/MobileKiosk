package com.example.mobilekiosk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class setmenuFrag extends Fragment {
    Button setMenu1,setMenu2,setMenu3,setMenu4;
    TextView set1_price,set2_price,set3_price,set4_price;
    setmenuFrag() {

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_setmenu,container,false);
        setMenu1 = (Button) v.findViewById(R.id.set1);
        setMenu2 = (Button) v.findViewById(R.id.set2);
        setMenu3 = (Button) v.findViewById(R.id.set3);
        setMenu4 = (Button) v.findViewById(R.id.set4);
        set1_price = (TextView) v.findViewById(R.id.set1_price);
        set2_price = (TextView) v.findViewById(R.id.set2_price);
        set3_price = (TextView) v.findViewById(R.id.set3_price);
        set4_price = (TextView) v.findViewById(R.id.set4_price);

        setMenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int setmenu1_price_len = set1_price.getText().toString().length();
                int setmenu1_price_value = Integer.parseInt(set1_price.getText().toString().substring(4,setmenu1_price_len-1));
                ((OrderActivity)getActivity()).changeValue(setmenu1_price_value);
                ((OrderActivity)getActivity()).addToCart("봉구스밥버거 세트", "4000");
            }
        });

        setMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int setmenu2_price_len = set2_price.getText().toString().length();
                int setmenu2_price_value = Integer.parseInt(set2_price.getText().toString().substring(4,setmenu2_price_len-1));
                ((OrderActivity)getActivity()).changeValue(setmenu2_price_value);
                ((OrderActivity)getActivity()).addToCart("햄밥버거 세트", "4500");
            }
        });
        setMenu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int setmenu3_price_len = set3_price.getText().toString().length();
                int setmenu3_price_value = Integer.parseInt(set3_price.getText().toString().substring(4,setmenu3_price_len-1));
                ((OrderActivity)getActivity()).changeValue(setmenu3_price_value);
                ((OrderActivity)getActivity()).addToCart("치즈밥버거 세트", "5000");
            }
        });

        setMenu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int setmenu4_price_len = set4_price.getText().toString().length();
                int setmenu4_price_value = Integer.parseInt(set4_price.getText().toString().substring(4,setmenu4_price_len-1));
                ((OrderActivity)getActivity()).changeValue(setmenu4_price_value);
                ((OrderActivity)getActivity()).addToCart("햄치즈밥버거 세트", "5500");
            }
        });

        return v;
    }
}
