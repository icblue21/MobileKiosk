package com.example.mobilekiosk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class sidemenuFrag extends Fragment {
    private Button sideMenu1,sideMenu2;
    private TextView side1_price,side2_price;
    sidemenuFrag() {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_sidemenu,container,false);
        sideMenu1 = (Button) v.findViewById(R.id.sidemenu1);
        sideMenu2 = (Button) v.findViewById(R.id.sidemenu2);
        side1_price = (TextView) v.findViewById(R.id.side1_price);
        side2_price = (TextView) v.findViewById(R.id.side2_price);

        sideMenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sidemenu1_price_len = side1_price.getText().toString().length();
                int sidemenu1_price_value = Integer.parseInt(side1_price.getText().toString().substring(4,sidemenu1_price_len-1));
                ((OrderActivity)getActivity()).changeValue(sidemenu1_price_value);
                ((OrderActivity)getActivity()).addToCart("컵라면", "1000");
            }
        });

        sideMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sidemenu2_price_len = side2_price.getText().toString().length();
                int sidemenu2_price_value = Integer.parseInt(side2_price.getText().toString().substring(4,sidemenu2_price_len-1));
                ((OrderActivity)getActivity()).changeValue(sidemenu2_price_value);
                ((OrderActivity)getActivity()).addToCart("감자튀김", "2000");
            }
        });

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
