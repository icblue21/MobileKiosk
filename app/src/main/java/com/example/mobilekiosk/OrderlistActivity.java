package com.example.mobilekiosk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrderlistActivity extends AppCompatActivity {
    DatabaseReference Database = FirebaseDatabase.getInstance().getReference();
    OrderData OrderList;
    LinearLayout lm2;
    LinearLayout lm1;
    int ListCount;
    int totalquantity;
    int totalfee;
    String id;
    int count;
    String SetOreder;
    String SetOreder2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);
        Intent intent = getIntent();
        id = intent.getStringExtra("userID");
        Initialize();
        GetFireData();

    }

    public void Initialize() {
        lm2 = (LinearLayout)findViewById(R.id.OrderHistoryView);
        lm1 = (LinearLayout)findViewById(R.id.OrderHistoryView2);
        ListCount = 0;
        totalquantity = 0;
        totalfee = 0;
        count = 0;
    }

    void GetFireData() {
        Database.addListenerForSingleValueEvent(new ValueEventListener() {
            String str;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.child("StoreDB").getChildren()) {
                    for (DataSnapshot snapshot2 : dataSnapshot.child("StoreDB").child(snapshot.getKey()).child("Proceeding").getChildren()) {
                        for (DataSnapshot snapshot3 : dataSnapshot.child("StoreDB").child(snapshot.getKey()).child("Proceeding").child(snapshot2.getKey()).getChildren()){
                            str = "StoreDB "+snapshot.getKey()+" Proceeding " + snapshot2.getKey()+" " + snapshot3.getKey();
                            upList(str,snapshot2.getKey(),1);
                        }

                    }
                    for (DataSnapshot snapshot2 : dataSnapshot.child("StoreDB").child(snapshot.getKey()).child("end").getChildren()) {
                        for (DataSnapshot snapshot3 : dataSnapshot.child("StoreDB").child(snapshot.getKey()).child("end").child(snapshot2.getKey()).getChildren()){
                            str = "StoreDB "+snapshot.getKey()+" end " + snapshot2.getKey()+" " + snapshot3.getKey();
                            upList(str,snapshot2.getKey(),2);
                        }

                    }
                    //OrderList = snapshot.getValue(OrderData.class);
                    // String str = snapshot.getKey();
                    // upList(str);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    void upList(String str,String str2,int set) {

        final LinearLayout ll = new LinearLayout(this);
        TextView temp = new TextView(this);
        //String str = "상품명: " + OrderList.name + "   가격: " + OrderList.totalprice + "  주문수량: " + OrderList.quantity;
        //String str = "상품명: ";
        temp.setText(str2);
        temp.setTextSize(22);
        final LinearLayout.LayoutParams ll2 = new LinearLayout.LayoutParams(120,120);
        final Button btn = new Button(this);
        final Button btn2 = new Button(this);
        final String position = str;
        final String position2 = str2;
        final LinearLayout ButtonLocation;
        btn.setId(count);
        count++;
        btn.setText("보기");

        btn2.setId(count);
        count++;
        btn2.setText("취소");
        //btn.setLayoutParams(ll2);

        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                SetOreder = position;
                SetOreder2 = position2;
                ShowPopUp();

            }

        });


        ll.addView(temp);
        ll.addView(btn);
        ll.addView(btn2);

        if(set == 2) {
            ButtonLocation = lm2;
            lm2.addView(ll);
            //totalquantity += OrderList.quantity;
            //totalfee += OrderList.totalprice;
        }else{
            ButtonLocation = lm1;
            lm1.addView(ll);
        }
        btn2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String[] root = position.split(" ");
                ButtonLocation.removeView(ll);
                Database.child(root[0]).child(root[1]).child(root[2]).child(root[3]).setValue(null);

            }

        });

    }

    void ShowPopUp(){


        Database.addListenerForSingleValueEvent(new ValueEventListener() {
            String Message="";
            String temp;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String[] root = SetOreder.split(" ");
                for (DataSnapshot snapshot : dataSnapshot.child(root[0]).child(root[1]).child(root[2]).child(root[3]).child(id).getChildren()) {
                    OrderList = snapshot.getValue(OrderData.class);
                    temp = "상품명: " + OrderList.name + "   가격: " + OrderList.totalprice + "  주문수량: " + OrderList.quantity;
                    Message+=temp+'\n';
                    totalquantity += OrderList.quantity;
                    totalfee += OrderList.totalprice;
                }

                Message+="총 가격: "+totalfee+"총 수량: "+totalquantity;

                MesaageUp(Message);
                totalquantity = 0;
                totalfee = 0;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    void MesaageUp(String str){
        // 다이얼로그 바디
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
        // 메세지
        alert_confirm.setMessage(str);
        // 확인 버튼 리스너
        alert_confirm.setPositiveButton("확인", null);
        // 다이얼로그 생성
        AlertDialog alert = alert_confirm.create();

        // 다이얼로그 타이틀
        alert.setTitle(SetOreder2);
        // 다이얼로그 보기
        alert.show();


    }

}