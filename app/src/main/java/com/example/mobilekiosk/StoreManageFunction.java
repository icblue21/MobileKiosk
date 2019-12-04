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

public class StoreManageFunction extends AppCompatActivity {
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
    String mystore;
    String changeroot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_manage_function);
        Intent intent = getIntent();

        id = intent.getStringExtra("userID");
        mystore = intent.getStringExtra("storeID");

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
                    if(snapshot.getKey().equals(mystore)) {
                        for (DataSnapshot snapshot2 : dataSnapshot.child("StoreDB").child(snapshot.getKey()).child("Proceeding").getChildren()) {
                            for (DataSnapshot snapshot3 : dataSnapshot.child("StoreDB").child(snapshot.getKey()).child("Proceeding").child(snapshot2.getKey()).getChildren()) {
                                if (false == snapshot3.getKey().equals("Store")) {


                                    str = "StoreDB " + snapshot.getKey() + " Proceeding " + snapshot2.getKey() + " " + snapshot3.getKey();
                                    upList(str, snapshot2.getKey(), 1);

                                }
                            }

                        }
                        for (DataSnapshot snapshot2 : dataSnapshot.child("StoreDB").child(snapshot.getKey()).child("end").getChildren()) {

                            for (DataSnapshot snapshot3 : dataSnapshot.child("StoreDB").child(snapshot.getKey()).child("end").child(snapshot2.getKey()).getChildren()) {
                                if (false == snapshot3.getKey().equals("Store")) {

                                    str = "StoreDB " + snapshot.getKey() + " end " + snapshot2.getKey() + " " + snapshot3.getKey();

                                    upList(str, snapshot2.getKey(), 2);

                                }

                            }
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

    void ChangeDB() {

        Database.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String []root = changeroot.split(" ");
                OrderData []tempOrderList = new OrderData[100];
                int tempcount = 0;
                for (DataSnapshot snapshot : dataSnapshot.child(root[0]).child(root[1]).child(root[2]).child(root[3]).child(root[4]).getChildren()) {
                    tempOrderList[tempcount] = snapshot.getValue(OrderData.class);
                    tempcount++;
                }
                Database.child(root[0]).child(root[1]).child(root[2]).child(root[3]).setValue(null);
                Database.child(root[0]).child(root[1]).child("end").child(root[3]).child("Store").setValue(mystore);
                for(int j = 0;j<100;j++){
                    try {
                        Database.child(root[0]).child(root[1]).child("end").child(root[3]).child(root[4]).child("MenuList"+j).setValue(tempOrderList[j]);
                    }
                    catch(Exception e){
                        break;
                    }
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
        final Button btn3 = new Button(this);
        final String position = str;
        final String position2 = str2;
        //final String position3 = str2;
        final LinearLayout ButtonLocation;
        btn.setId(count);
        count++;
        btn.setText("보기");

        btn2.setId(count);
        count++;
        btn2.setText("취소");

        btn.setId(count);
        count++;
        btn3.setText("확인");
        btn3.setId(count);
        //btn.setLayoutParams(ll2);

        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                SetOreder = position;
                SetOreder2 = position2;
                ShowPopUp();

            }

        });

        if(set == 2) {
            ButtonLocation = lm2;
            lm2.addView(ll);
            ll.addView(temp);
            ll.addView(btn);
            ll.addView(btn2);
            //totalquantity += OrderList.quantity;
            //totalfee += OrderList.totalprice;
        }else{
            ll.addView(temp);
            ll.addView(btn3);
            ll.addView(btn);
            ll.addView(btn2);

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
        btn3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String[] root = position.split(" ");

                changeroot = position;
                ChangeDB();
                root[2] = "end";
                String str = "";
                for(int i = 0;i<4;i++){
                    str+=root[i];
                    str+=" ";
                }
                str+=root[4];
                upList(str, root[3],2);
                ButtonLocation.removeView(ll);
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
                for (DataSnapshot snapshot : dataSnapshot.child(root[0]).child(root[1]).child(root[2]).child(root[3]).child(root[4]).getChildren()) {
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
