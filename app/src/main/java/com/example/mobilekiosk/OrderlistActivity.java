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
    int ListCount;
    int totalquantity;
    int totalfee;
    String id;
    int count;
    String SetOreder;

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

                for (DataSnapshot snapshot : dataSnapshot.child("OrderList").child(id).getChildren()) {
                    //OrderList = snapshot.getValue(OrderData.class);
                    String str = snapshot.getKey();
                    upList(str);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    void upList(String str) {

        final LinearLayout ll = new LinearLayout(this);
        TextView temp = new TextView(this);
        //String str = "상품명: " + OrderList.name + "   가격: " + OrderList.totalprice + "  주문수량: " + OrderList.quantity;
        //String str = "상품명: ";
        temp.setText(str);
        temp.setTextSize(30);
        final LinearLayout.LayoutParams ll2 = new LinearLayout.LayoutParams(120,120);
        final Button btn = new Button(this);
        final String position = str;
        btn.setId(count);
        count++;
        btn.setText("보기");
        //btn.setLayoutParams(ll2);

        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                SetOreder = position;
                ShowPopUp();

            }

        });
        ll.addView(temp);
        ll.addView(btn);

        lm2.addView(ll);
        //totalquantity += OrderList.quantity;
        //totalfee += OrderList.totalprice;

    }

    void ShowPopUp(){


        Database.addListenerForSingleValueEvent(new ValueEventListener() {
            String Message="";
            String temp;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.child("OrderList").child(id).child(SetOreder).getChildren()) {
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
        alert.setTitle(SetOreder);
        // 다이얼로그 보기
        alert.show();


    }

}



