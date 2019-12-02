package com.example.mobilekiosk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PayHistory extends AppCompatActivity implements View.OnClickListener{

    DatabaseReference Database = FirebaseDatabase.getInstance().getReference();
    MenuData MenuList[];
    LinearLayout lm;
    TextView TotalView;
    int totalfee;
    int totalquantity;
    String wholeInfo;
    ImageButton returnButton;
    String userID;
    String StoreId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_history);
        Intent intent = getIntent();

        MenuList = (MenuData[]) intent.getSerializableExtra("MenuData");
        userID = intent.getStringExtra("userID");
        wholeInfo = (String) intent.getSerializableExtra("wholeInfo");
        StoreId = intent.getStringExtra("storeid");



        Initialize();
        AddList();
        //GetFireData();
        upDB();


    }

    @Override
    public void onClick(View view) {

    }

    void SetListener(){


    }
    class GetOrder{
        int order;
        GetOrder(int n){
            order = n;
        }

    }

    public void Initialize(){
        lm = (LinearLayout)findViewById(R.id.PayHistoryText);
        returnButton = (ImageButton) findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),OrderChooseActivity.class);
                intent.putExtra("userID",userID);
                startActivity(intent);
            }
        });
        TotalView = (TextView)findViewById(R.id.textView12);
        totalfee = 0;
        totalquantity = 0;

    }
    void AddList(){
        //TextView temp = new TextView(this);
        for(int i = 0;i<100;i++){
            try {
                if (MenuList[i].GetQuntity() != 0) {
                    TextView temp = new TextView(this);
                    String str = "상품명: " + MenuList[i].GetName() + "\n가격: " + MenuList[i].GetTotal() + "\n주문수량: "+ MenuList[i].GetQuntity() + "\n";
                    temp.setText(str);
                    temp.setTextSize(25);
                    lm.addView(temp);
                    totalquantity+=MenuList[i].GetQuntity();
                    totalfee+=MenuList[i].GetTotal();
                }
            }catch(Exception e){
                break;
            }

        }
        //lm.addView(temp);
        TotalView.setText("Total        "+totalquantity+"       "+totalfee );

        // upDB();

    }
/*
    void GetFireData(){
        Database.addListenerForSingleValueEvent(new ValueEventListener() {
            String str;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.child("OrderCount").getChildren()) {
                    str = ""+snapshot.getValue();
                }
                upDB(str);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    */

    void upDB(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyyMMddHHmmss");
        String formatDate = sdfNow.format(date);
        String mydate = "";
        mydate+=formatDate;
        //String temp = str;
        //Integer i = Integer.valueOf(temp);
        //i++;
        Database.child("StoreDB").child(StoreId).child("Proceeding").child(mydate).child("Store").setValue(StoreId);
        for(int j = 0;j<100;j++){
            try {
                if (MenuList[j].GetQuntity() != 0) {
                    OrderData order = new OrderData(MenuList[j].GetName(), MenuList[j].GetQuntity(),MenuList[j].GetTotal());
                    Database.child("StoreDB").child(StoreId).child("Proceeding").child(mydate).child(userID).child("MenuList"+j).setValue(order);
                }
            }catch(Exception e){
                break;
            }

        }


        //Database.child("OrderCount").setValue(Integer.parseInt(str)+1);
        //Database.child("OrderCount").child("i").setValue(i);
        //Query query = FirebaseDatabase.getInstance().getReference().child("OrderList").orderByChild("name");
        //String data = query.toString();
    }

    public void onBackPressed() {

    }


/*
    ValueEventListener postListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                String key = postSnapshot.getKey();
                postSnapshot.getValue(OrderData.class);
               // String info = get.name;
                //TotalView.setText(info);
            }





        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.w("getFirebaseDatabase","loadPost:onCancelled", databaseError.toException());
        }
    };


*/


}