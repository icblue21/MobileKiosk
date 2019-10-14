package com.example.mobilekiosk;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.example.mobilekiosk.R;



public class MenuFragment4 extends Fragment {

    ImageButton button1;


    public MenuFragment4() {
        // required
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ConstraintLayout layout = (ConstraintLayout) inflater.inflate(R.layout.fragment_menuselect4, container, false);
        button1 = (ImageButton) layout.findViewById(R.id.imageButton8);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ontime.ontimePickerset("기타이다", 4);
            }
        });

        return layout;
    }
}