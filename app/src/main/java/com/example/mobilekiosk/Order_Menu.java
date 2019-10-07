package com.example.mobilekiosk;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class Order_Menu extends AppCompatActivity implements View.OnClickListener, BusProvider.OntimeListener{

    Button mybutton1; //프레그먼트1 호출
    Button mybutton2; //프레그먼트2 호출
    Button mybutton3; //프레그먼츠3 호출
    Button mybutton4; //프레그먼트4 호출

    int SetId;   //버튼 id값 세팅
    int SetCode; //메뉴추가마다 1씩 증가하여 생성할 MenuData 를 지정
    //int  k[];
    MenuData MenuList[];//메뉴 데이터 배열
    LinearLayout lm;
    LinearLayout.LayoutParams params;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__menu);

        this.InitializeView();
        this.SetListener();

    }


    @Override  //프레그먼트 이벤트 발생 수신
    public void ontimePickerset(String name, int price) {
        MenuList[SetCode] = new MenuData(name, price,1);//메뉴객체생성 이름,가격,수량(초기1)
        addlist(name,price);

    }


    public void InitializeView()
    {

        mybutton1 = (Button)findViewById(R.id.button1);
        mybutton2 = (Button)findViewById(R.id.button2);
        mybutton3 = (Button)findViewById(R.id.button4);
        mybutton4 = (Button)findViewById(R.id.button);

        SetId = 0;
        SetCode = 0;
       // k = new int[100];
        lm = (LinearLayout) findViewById(R.id.LinearLayout1);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        MenuList = new MenuData[100];

    }

    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.button1:
                callFragment(1);
                break;

            case R.id.button2:
                callFragment(2);
                break;

            case R.id.button4:
                callFragment(3);
                break;

            case R.id.button:
                callFragment(4);
                break;

        }
    }
    public void SetListener()
    {
        mybutton1.setOnClickListener(this);
        mybutton2.setOnClickListener(this);
        mybutton3.setOnClickListener(this);
        mybutton4.setOnClickListener(this);

    }

    private void callFragment(int frament_no){

        // 프래그먼트 사용을 위해
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (frament_no){
            case 1:
                // '프래그먼트1' 호출
                MenuFragment1 fragment1 = new MenuFragment1();
                transaction.replace(R.id.fragframe, fragment1);
                transaction.commit();
                break;

            case 2:
                // '프래그먼트2' 호출
                MenuFragment2 fragment2 = new MenuFragment2();
                transaction.replace(R.id.fragframe, fragment2);
                transaction.commit();
                break;
            case 3:
                // '프래그먼트3' 호출
                MenuFragment3 fragment3 = new MenuFragment3();
                transaction.replace(R.id.fragframe, fragment3);
                transaction.commit();
                break;
            case 4:
                // '프래그먼트4' 호출
                MenuFragment4 fragment4 = new MenuFragment4();
                transaction.replace(R.id.fragframe, fragment4);
                transaction.commit();
                break;
        }

    }

    //메뉴 목록 레이아웃 할당
    public void addlist(String name, int price){

         // LinearLayout 생성
        final LinearLayout ll = new LinearLayout(this);
        final LinearLayout.LayoutParams ll2 = new LinearLayout.LayoutParams(120,120);
        final LinearLayout.LayoutParams ll3 = new LinearLayout.LayoutParams(160,120);
        ll.setOrientation(LinearLayout.HORIZONTAL);

        // TextView 생성
        TextView tvProdc = new TextView(this);
        tvProdc.setText("상품명: "+ name + " ");

        final TextView tvAge = new TextView(this);
        tvAge.setText("   수량" + 1 + "  ");

        final TextView tvPrice = new TextView(this);
        tvPrice.setText(""+price+"");

        // 버튼 생성

        final Button btn = new Button(this);
        final Button btn2 = new Button(this);
        final Button btn3 = new Button(this);

        // setId 버튼에 대한 키값
        final int position = SetCode; //생성 순서 고정
        btn.setId(SetId+1);
        btn.setText("+");
        btn.setLayoutParams(ll2);

        btn2.setId(SetId + 2);
        btn2.setText("-");
        btn2.setLayoutParams(ll2);

        btn3.setId(SetId + 3);
        btn3.setText("삭제");
        btn3.setLayoutParams(ll3);
        //k[SetCode] = 1;

         btn.setOnClickListener(new View.OnClickListener() {

             public void onClick(View v) {

                 //k[position]++;
                 int i = position;
                 MenuList[i].SetQuntity(MenuList[i].GetQuntity()+1);//수량 증가
                 tvAge.setText("   수량" + MenuList[i].GetQuntity() + "  ");
                 tvPrice.setText(""+MenuList[i].GetFee());

             }

         });
        btn2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                //k[position]--;
                int i = position;//
                MenuList[i].SetQuntity(MenuList[i].GetQuntity()-1);//수량 감소
                tvAge.setText("   수량" + MenuList[i].GetQuntity() + "  ");
                tvPrice.setText(""+MenuList[i].GetFee());

            }

        });
        btn3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                lm.removeView(ll);
            }

        });

         //버튼 , 텍스트뷰 add
         ll.addView(tvProdc);
         ll.addView(btn);
         ll.addView(tvAge);
         ll.addView(btn2);
         ll.addView(tvPrice);
         ll.addView(btn3);

         //LinearLayout 정의된거 add

         lm.addView(ll);
         SetId = SetId+3;
         SetCode++;

    }



}




