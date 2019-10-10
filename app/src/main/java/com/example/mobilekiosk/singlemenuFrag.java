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

public class singlemenuFrag extends Fragment {
    private Button singleMenu1,singleMenu2,singleMenu3,singleMenu4;
    private TextView single1_price,single2_price,single3_price,single4_price;
    singlemenuFrag() {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_singlemenu,container,false);

        singleMenu1 = (Button) v.findViewById(R.id.single1);
        singleMenu2 = (Button) v.findViewById(R.id.single2);
        singleMenu3 = (Button) v.findViewById(R.id.single3);
        singleMenu4 = (Button) v.findViewById(R.id.single4);
        single1_price = (TextView) v.findViewById(R.id.single1_price);
        single2_price = (TextView) v.findViewById(R.id.single2_price);
        single3_price = (TextView) v.findViewById(R.id.single3_price);
        single4_price = (TextView) v.findViewById(R.id.single4_price);

        singleMenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int singlemenu1_price_len = single1_price.getText().toString().length();
                int singlemenu1_price_value = Integer.parseInt(single1_price.getText().toString().substring(4,singlemenu1_price_len-1));
                ((OrderActivity)getActivity()).changeValue(singlemenu1_price_value);
                ((OrderActivity)getActivity()).addToCart("봉구스밥버거", "2500");
            }
        });

        singleMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int singlemenu2_price_len = single2_price.getText().toString().length();
                int singlemenu2_price_value = Integer.parseInt(single2_price.getText().toString().substring(4,singlemenu2_price_len-1));
                ((OrderActivity)getActivity()).changeValue(singlemenu2_price_value);
                ((OrderActivity)getActivity()).addToCart("햄밥버거", "3000");
            }
        });
        singleMenu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int singlemenu3_price_len = single3_price.getText().toString().length();
                int singlemenu3_price_value = Integer.parseInt(single3_price.getText().toString().substring(4,singlemenu3_price_len-1));
                ((OrderActivity)getActivity()).changeValue(singlemenu3_price_value);
                ((OrderActivity)getActivity()).addToCart("치즈밥버거", "3500");
            }
        });

        singleMenu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int singlemenu4_price_len = single4_price.getText().toString().length();
                int singlemenu4_price_value = Integer.parseInt(single4_price.getText().toString().substring(4,singlemenu4_price_len-1));
                ((OrderActivity)getActivity()).changeValue(singlemenu4_price_value);
                ((OrderActivity)getActivity()).addToCart("햄치즈밥버거", "4000");
            }
        });

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
