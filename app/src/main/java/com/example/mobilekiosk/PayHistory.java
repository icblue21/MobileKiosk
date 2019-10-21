package com.example.mobilekiosk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;




public class PayHistory extends AppCompatActivity implements View.OnClickListener{

    //DatabaseReference Database = FirebaseDatabase.getInstance().getReference();
    MenuData MenuList[];
    LinearLayout lm;
    TextView TotalView;
    int totalfee;
    int totalquantity;
    ImageButton returnButton = (ImageButton) findViewById(R.id.returnButton);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_history);
        Intent intent = getIntent();
        MenuList = (MenuData[])intent.getSerializableExtra("MenuData");
        Initialize();
        AddList();
    }




    @Override
    public void onClick(View view) {

        switch (view.getId()){

        }
    }

    void SetListener(){


    }

    public void Initialize(){
        lm = (LinearLayout)findViewById(R.id.PayHistoryText);
        TotalView = (TextView)findViewById(R.id.textView12);
        totalfee = 0;
        totalquantity = 0;

    }
    void AddList(){

        for(int i = 0;i<100;i++){
            try {
                if (MenuList[i].GetQuntity() != 0) {
                    TextView temp = new TextView(this);
                    String str = "상품명: " + MenuList[i].GetName() + "   가격: " + MenuList[i].GetTotal() + "  주문수량: "+ MenuList[i].GetQuntity();
                    temp.setText(str);
                    temp.setTextSize(30);
                    lm.addView(temp);
                    totalquantity+=MenuList[i].GetQuntity();
                    totalfee+=MenuList[i].GetTotal();
                }
            }catch(Exception e){
                break;
            }

        }
        TotalView.setText("Total        "+totalquantity+"       "+totalfee);
       // upDB();

    }
    /*
    void upDB(){


        OrderData order = new OrderData("햄버거", 5,1000);
        Database.child("OrderList").child("order1").setValue(order);

        Query query = FirebaseDatabase.getInstance().getReference().child("OrderList").orderByChild("name");
        //String data = query.toString();
        query.addListenerForSingleValueEvent(postListener);

    }
    */



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
    public class OrderData {

            public String name;
            public int quantity;
            public int totalprice;

        public OrderData() {
                // Default constructor required for calls to DataSnapshot.getValue(User.class)
            }

        public OrderData(String name, int quantity, int price) {
                this.name = name;
                this.quantity = quantity;
                this.totalprice = price;

        }
    }

}
