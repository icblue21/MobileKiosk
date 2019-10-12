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

public class newmenuFrag extends Fragment {
    private Button newMenu1,newMenu2;
    private TextView newmenu1_price, newmenu2_price;
    newmenuFrag() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //TableLayout layout  = (TableLayout) inflater.inflate(R.layout.frag_newmenu,container,false);
        View v = inflater.inflate(R.layout.frag_newmenu,container,false);
        newMenu1 = (Button) v.findViewById(R.id.newmenu1);
        newMenu2 = (Button) v.findViewById(R.id.newmenu2);
        newmenu1_price = (TextView) v.findViewById(R.id.set3_price);
        newmenu2_price = (TextView) v.findViewById(R.id.set4_price);

        newMenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newmenu1_price_len = newmenu1_price.getText().toString().length();
                int newmenu1_price_value = Integer.parseInt(newmenu1_price.getText().toString().substring(4,newmenu1_price_len-1));
                ((OrderActivity)getActivity()).changeValue(newmenu1_price_value);
                ((OrderActivity)getActivity()).addToCart("신메뉴1", "3500");
            }
        });

        newMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newmenu2_price_len = newmenu2_price.getText().toString().length();
                int newmenu2_price_value = Integer.parseInt(newmenu2_price.getText().toString().substring(4,newmenu2_price_len-1));
                ((OrderActivity)getActivity()).changeValue(newmenu2_price_value);
                ((OrderActivity)getActivity()).addToCart("신메뉴2", "4000");
            }
        });
        return v;
    }
}
