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

import org.w3c.dom.Text;


public class PayHistory extends AppCompatActivity implements View.OnClickListener{

    //DatabaseReference Database = FirebaseDatabase.getInstance().getReference();
    MenuData MenuList[];
    LinearLayout lm;
    TextView TotalView;
    int totalfee;
    int totalquantity;
    String wholeInfo;
    ImageButton returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_history);
        Intent intent = getIntent();
        MenuList = (MenuData[])intent.getSerializableExtra("MenuData");
        wholeInfo = (String)intent.getSerializableExtra("wholeInfo");
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
        returnButton = (ImageButton) findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),OrderChooseActivity.class);
                startActivity(intent);
            }
        });
        TotalView = (TextView)findViewById(R.id.textView12);
        totalfee = 0;
        totalquantity = 0;

    }
    void AddList(){
        TextView temp = new TextView(this);
        //temp.setTextSize(25);

        for(int i = 0;i<100;i++){
            try {
                if (MenuList[i].GetQuntity() != 0) {
                    //TextView temp = new TextView(this);
                    //String str = "상품명: " + MenuList[i].GetName() + "\n가격: " + MenuList[i].GetTotal() + "\n주문수량: "+ MenuList[i].GetQuntity() + "\n";
                    temp.setText(wholeInfo);
                    temp.setTextSize(25);
                    //lm.addView(temp);
                    totalquantity+=MenuList[i].GetQuntity();
                    totalfee+=MenuList[i].GetTotal();
                }
            }catch(Exception e){
                break;
            }

        }
        lm.addView(temp);
        //temp.setText(wholeInfo);
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
