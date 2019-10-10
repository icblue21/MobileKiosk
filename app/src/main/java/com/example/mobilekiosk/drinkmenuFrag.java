package com.example.mobilekiosk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class drinkmenuFrag extends Fragment {
    Button drinkButton1;
    Button drinkButton2;
    TextView coke_price;
    TextView cider_price;
    int button_count = 0;
    drinkmenuFrag() {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_drinkmenu,container,false);

        drinkButton1 = (Button) v.findViewById(R.id.cokebutton);
        drinkButton2 = (Button) v.findViewById(R.id.ciderbutton);
        coke_price = (TextView) v.findViewById(R.id.set3_price);
        cider_price = (TextView) v.findViewById(R.id.set4_price);

        drinkButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int coke_price_len = coke_price.getText().toString().length();
                int coke_price_value = Integer.parseInt(coke_price.getText().toString().substring(4,coke_price_len-1));
                ((OrderActivity)getActivity()).changeValue(coke_price_value);
                ((OrderActivity)getActivity()).addToCart("콜라", "1200");

            }
        });

        drinkButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int cider_price_len = cider_price.getText().toString().length();
                int cider_price_value = Integer.parseInt(cider_price.getText().toString().substring(4,cider_price_len-1));
                ((OrderActivity)getActivity()).changeValue(cider_price_value);
                ((OrderActivity)getActivity()).addToCart("사이다", "1000");
            }
        });

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}
