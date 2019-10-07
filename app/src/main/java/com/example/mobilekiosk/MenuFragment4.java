package com.example.mobilekiosk;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;


public class MenuFragment4 extends Fragment {

    ImageButton button1;
    private BusProvider.OntimeListener ontime;

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
        if (context instanceof BusProvider.OntimeListener) {
            ontime = (BusProvider.OntimeListener) context;

        } else {


        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        ontime = null;
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

                ontime.ontimePickerset("기타", 15000);
            }
        });

        return layout;
    }
}