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
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.example.mobilekiosk.BusProvider;
import com.example.mobilekiosk.R;



public class MenuFragment1 extends Fragment {
    int resId;
    ImageButton button[];
    Button btn[];
    TextView text[];
    ButtonList BList[];

    private BusProvider.OntimeListener ontime;
    public MenuFragment1()
    {
        // required
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof BusProvider.OntimeListener){
            ontime = (BusProvider.OntimeListener) context;

        }else{

        }

    }
    @Override
    public void  onDetach() {
        super.onDetach();
        ontime = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ConstraintLayout layout = (ConstraintLayout) inflater.inflate(R.layout.fragment_menuselect1, container, false);

        button = new ImageButton[3];
        text = new TextView[3];
        btn = new Button[2];
        btn[0] = (Button)layout.findViewById(R.id.button6);
        btn[1] = (Button)layout.findViewById(R.id.button7);



        for(int i = 0;i<3;i++){
            String bt = "fragm"+(i+1);
            String tv = "fragt"+(i+1);
            resId= getResources().getIdentifier(bt, "id", "com.example.mobilekiosk");
            button[i] = (ImageButton)layout.findViewById(resId);
            resId= getResources().getIdentifier(tv, "id", "com.example.mobilekiosk");
            text[i] = (TextView) layout.findViewById(resId);
        }


        BList = new ButtonList[3];
        BList[0] =  new ButtonList(R.drawable.pic1,"햄버거3",1000);
        BList[1] =  new ButtonList(R.drawable.bb2,"햄버거2",2000);
        BList[2] =  new ButtonList(R.drawable.bb3,"햄버거1",3000);


        button[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ontime.ontimePickerset("작동",1000);
                SubListenner(0);
                //button[0].setImageResource(R.drawable.burger);
            }
        });

        button[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubListenner(1);
            }
        });
        button[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubListenner(2);
            }
        });
        btn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //정렬 알고리즘으로 대체
                ButtonList temp;
                temp = BList[2];
                BList[2] = BList[0];
                BList[0] =  temp;
                ReDrawButton();
            }
        });
        btn[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //정렬 알고리즘 대체
                ButtonList temp;
                temp = BList[2];
                BList[2] = BList[0];
                BList[0] =  temp;
                ReDrawButton();
            }
        });


        ReDrawButton();
        return layout;
    }

    class ButtonList{
         public int img;
         public String Fname;
         public int price;
         ButtonList(int id, String name, int price){
             this.img = id;
             this.Fname = name;
             this.price = price;
         }
    }
    void SubListenner(int num) {
        switch (num) {
            case 0:

                ontime.ontimePickerset(BList[0].Fname,BList[0].price);
                break;

            case 1:

                ontime.ontimePickerset(BList[1].Fname,BList[1].price);
                break;

            case 2:

                ontime.ontimePickerset(BList[2].Fname,BList[2].price);
                break;
        }
    }
    void ReDrawButton(){
        for(int i = 0;i<3;i++){
            String bt = "fragm"+(i+1);
            String tv = "fragt"+(i+1);
            resId= getResources().getIdentifier(bt, "id", "com.example.a0923firebase");
            button[i].setImageResource(BList[i].img);
            resId= getResources().getIdentifier(tv, "id", "com.example.a0923firebase");
            text[i].setText("가격: "+BList[i].price);
        }

    }

}


