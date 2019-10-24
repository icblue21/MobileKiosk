package com.example.mobilekiosk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrderlistActivity extends AppCompatActivity implements BusProvider.OntimeListener {
    DatabaseReference Database = FirebaseDatabase.getInstance().getReference();
    OrderData OrderList;
    LinearLayout lm2;
    int ListCount;
    int totalquantity;
    int totalfee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);
        Initialize();

        GetFireData();


    }

    public void Initialize() {
        lm2 = (LinearLayout)findViewById(R.id.OrderHistoryView);
        ListCount = 0;
        totalquantity = 0;
        totalfee = 0;
    }

    void GetFireData() {
        Database.addListenerForSingleValueEvent(new ValueEventListener() {
            String str;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.child("OrderList").child("order1").getChildren()) {
                    OrderList = snapshot.getValue(OrderData.class);
                    upList();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    void upList() {
        TextView temp = new TextView(this);
        String str = "상품명: " + OrderList.name + "   가격: " + OrderList.totalprice + "  주문수량: " + OrderList.quantity;
        //String str = "상품명: ";
        temp.setText(str);
        temp.setTextSize(30);
        lm2.addView(temp);
        //totalquantity += OrderList.quantity;
        //totalfee += OrderList.totalprice;

    }
    public void ontimePickerset(String name, int price) {
        if(price<0) {
            finish();
        }
    }


}



