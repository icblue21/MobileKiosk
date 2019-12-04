package com.example.mobilekiosk;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import androidx.fragment.app.Fragment;


public class MenuFragment1 extends Fragment {
    int resId;
    ImageButton button[];
    Button btn[];
    TextView text[];
    ButtonList BList[];

    private BusProvider.OntimeListener ontime;

    public MenuFragment1() {
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
        ConstraintLayout layout = (ConstraintLayout) inflater.inflate(R.layout.fragment_menuselect1, container, false);

        button = new ImageButton[6];
        text = new TextView[6];
        btn = new Button[2];
        btn[0] = (Button) layout.findViewById(R.id.button6);
        btn[1] = (Button) layout.findViewById(R.id.button7);


        for (int i = 0; i < 6; i++) {
            String bt = "fragm" + (i + 1);
            String tv = "fragt" + (i + 1);
            resId = getResources().getIdentifier(bt, "id", "com.example.mobilekiosk");
            button[i] = (ImageButton) layout.findViewById(resId);
            resId = getResources().getIdentifier(tv, "id", "com.example.mobilekiosk");
            text[i] = (TextView) layout.findViewById(resId);
        }


        BList = new ButtonList[6];
        BList[0] = new ButtonList(R.drawable.bb1, "햄쏘야", 1000);
        BList[1] = new ButtonList(R.drawable.bb2, "햄치즈쏘야", 2500);
        BList[2] = new ButtonList(R.drawable.bb3, "에그김치제육", 1500);
        BList[3] = new ButtonList(R.drawable.bb4, "치즈맛있새우", 4000);
        BList[4] = new ButtonList(R.drawable.bb5, "에그치즈닭갈비", 3500);
        BList[5] = new ButtonList(R.drawable.bb6, "마요오므라이스", 500);


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
        button[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubListenner(3);
            }
        });
        button[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubListenner(4);
            }
        });
        button[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubListenner(5);
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
                insertion_sort_name(BList,6);
                ReDrawButton();
            }
        });

        btn[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //가격 정렬
                insertion_sort_price(BList,6);
                ReDrawButton();
            }
        });

        ReDrawButton();
        return layout;
    }

    class ButtonList {
        public int img;
        public String Fname;
        public int price;

        ButtonList(int id, String name, int price) {
            this.img = id;
            this.Fname = name;
            this.price = price;
        }
    }

    void SubListenner(int num) {
        switch (num) {
            case 0:

                ontime.ontimePickerset(BList[0].Fname, BList[0].price);
                ((Order_Menu)getActivity()).setResultView(BList[0].price);
                break;

            case 1:

                ontime.ontimePickerset(BList[1].Fname, BList[1].price);
                ((Order_Menu)getActivity()).setResultView(BList[1].price);
                break;

            case 2:

                ontime.ontimePickerset(BList[2].Fname, BList[2].price);
                ((Order_Menu)getActivity()).setResultView(BList[2].price);
                break;

            case 3:

                ontime.ontimePickerset(BList[3].Fname, BList[3].price);
                ((Order_Menu)getActivity()).setResultView(BList[3].price);
                break;
            case 4:

                ontime.ontimePickerset(BList[4].Fname, BList[4].price);
                ((Order_Menu)getActivity()).setResultView(BList[4].price);
                break;
            case 5:

                ontime.ontimePickerset(BList[5].Fname, BList[5].price);
                ((Order_Menu)getActivity()).setResultView(BList[5].price);
                break;
        }
    }

    void ReDrawButton() {
        for (int i = 0; i < 6; i++) {
            String bt = "fragm" + (i + 1);
            String tv = "fragt" + (i + 1);
            resId = getResources().getIdentifier(bt, "id", "com.example.mobilekiosk");
            button[i].setImageResource(BList[i].img);
            resId = getResources().getIdentifier(tv, "id", "com.example.mobilekiosk");
            text[i].setTextSize(20);
            text[i].setText(BList[i].Fname + "\n (" + BList[i].price + "원)");
        }

    }

    void insertion_sort_price(ButtonList list[], int n) {
        int i, j;
        ButtonList key;

        for (i = 1; i < n; i++) {
            key = list[i];

            for (j = i - 1; j >= 0 && list[j].price > key.price; j--) {
                list[j + 1] = list[j];
            }
            list[j + 1] = key;
        }
    }

    void insertion_sort_name(ButtonList list[], int n) {
        int i, j;
        ButtonList key;

        for (i = 1; i < n; i++) {
            key = list[i];
            for (j = i - 1; j >= 0 && 0 < list[j].Fname.compareTo(key.Fname); j--) {
                list[j + 1] = list[j];
            }
            list[j + 1] = key;
        }
    }
}


