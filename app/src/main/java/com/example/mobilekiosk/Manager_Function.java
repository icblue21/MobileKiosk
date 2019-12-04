package com.example.mobilekiosk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;


public class Manager_Function extends AppCompatActivity implements View.OnClickListener {

    Spinner SotoreList; //확인할 목록 선택
    LinearLayout OrderView; //목록뷰
    Button confirmB; //확인버튼
    Button SearchB; //탐색 확인 버튼
    EditText SearchView;
    DatabaseReference Database = FirebaseDatabase.getInstance().getReference();
    MyDynamicArrayList OrderNumList;
    ArrayList<String> tempArraydata_A;
    ArrayList<String> tempArraydata_B;
    ArrayList<String> tempArraydata_C;
    ArrayList<String> tempArraydata_ALL;
    MyRedBlacktree Ordertree;
    OrderData[] OrderList;
    String testdata;
    int count;
    String SetOreder;
    String SetOreder2;
    int ListCount;
    int totalquantity;
    int totalfee;

    boolean DBState;
    int testcount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager__function);
        InitializeView();
        SetListener();
        GetDB_AllorderList();
        GetFireDataTree();


    }

    public void InitializeView() {
        SotoreList = (Spinner) findViewById(R.id.Store_spin);
        ArrayAdapter yearAdapter = ArrayAdapter.createFromResource(this,
                R.array.StoreList, android.R.layout.simple_spinner_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SotoreList.setAdapter(yearAdapter);
        OrderView = (LinearLayout) findViewById(R.id.MasterOrderList);
        confirmB = (Button) findViewById(R.id.ConfirmStore);
        SearchB = (Button) findViewById(R.id.SearchButton);
        SearchView = (EditText) findViewById(R.id.search_Oreder);
        testcount = 0;
        DBState = false;
        tempArraydata_A = new ArrayList();
        tempArraydata_B = new ArrayList();
        tempArraydata_C = new ArrayList();
        tempArraydata_ALL = new ArrayList();
        totalquantity = 0;
        totalfee = 0;
        count = 0;
        OrderList = new OrderData[50];
        Ordertree = new MyRedBlacktree();


    }
    public void listreset(){
        tempArraydata_A = new ArrayList();
        tempArraydata_B = new ArrayList();
        tempArraydata_C = new ArrayList();
        tempArraydata_ALL = new ArrayList();
        OrderList = new OrderData[50];
        Ordertree = new MyRedBlacktree();
        totalquantity = 0;
        totalfee = 0;
        count = 0;
        testcount = 0;
        DBState = false;
        GetDB_AllorderList();
        GetFireDataTree();
    }

    public void SetListener() {
        confirmB.setOnClickListener(this);
        SearchB.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ConfirmStore:
                if (SotoreList.getSelectedItem().equals("전체")) {
                    ReDrawView(0);

                } else if (SotoreList.getSelectedItem().equals("봉구스")) {
                    ReDrawView(1);

                } else if (SotoreList.getSelectedItem().equals("호접몽")) {
                    ReDrawView(2);

                } else {
                    ReDrawView(3);
                }

                break;

            case R.id.SearchButton:
                //testdata = Ordertree.Treesearch("20191201004751");


                ReDrawView(4);


                break;

        }
    }

    void GetDB_AllorderList() {

        Database.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.child("StoreDB").getChildren()) {
                    for (DataSnapshot snapshot2 : dataSnapshot.child("StoreDB").child(snapshot.getKey()).child("Proceeding").getChildren()) {
                        //OrderNumList.addLast(snapshot2.getKey());
                        if (snapshot.getKey().equals("storeA")) {
                            tempArraydata_A.add(snapshot2.getKey());
                            tempArraydata_ALL.add(snapshot2.getKey());
                        } else if (snapshot.getKey().equals("storeB")) {
                            tempArraydata_B.add(snapshot2.getKey());
                            tempArraydata_ALL.add(snapshot2.getKey());
                        } else {
                            tempArraydata_C.add(snapshot2.getKey());
                            tempArraydata_ALL.add(snapshot2.getKey());
                        }


                    }
                    for (DataSnapshot snapshot2 : dataSnapshot.child("StoreDB").child(snapshot.getKey()).child("end").getChildren()) {

                        if (snapshot.getKey().equals("storeA")) {
                            tempArraydata_A.add(snapshot2.getKey());
                            tempArraydata_ALL.add(snapshot2.getKey());
                        } else if (snapshot.getKey().equals("storeB")) {
                            tempArraydata_B.add(snapshot2.getKey());
                            tempArraydata_ALL.add(snapshot2.getKey());
                        } else {
                            tempArraydata_C.add(snapshot2.getKey());
                            tempArraydata_ALL.add(snapshot2.getKey());
                        }
                    }
                }
                DBState = true;
                ReDrawView(0);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    void GetFireDataTree() {
        Database.addListenerForSingleValueEvent(new ValueEventListener() {
            String storename = null;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.child("StoreDB").getChildren()) {
                    for (DataSnapshot snapshot2 : dataSnapshot.child("StoreDB").child(snapshot.getKey()).child("Proceeding").getChildren()) {
                        int j = 0;
                        for (DataSnapshot snapshot3 : dataSnapshot.child("StoreDB").child(snapshot.getKey()).child("Proceeding").child(snapshot2.getKey()).getChildren()) {



                            if (j==0) {
                                String temps = "";
                                temps += snapshot3.getValue();
                                storename = temps;
                                j++;

                            } else {
                                int i = 0;
                                for (DataSnapshot snapshot5 : dataSnapshot.child("StoreDB").child(snapshot.getKey()).child("Proceeding").child(snapshot2.getKey()).child(snapshot3.getKey()).getChildren()) {
                                    OrderList[i] = snapshot5.getValue(OrderData.class);
                                    i++;
                                }
                                String DBroot = "StoreDB " + snapshot.getKey() + " Proceeding " + snapshot2.getKey();
                                Ordertree.root = Ordertree.insert(Ordertree, snapshot2.getKey(), snapshot.getKey(), snapshot3.getKey(), OrderList, DBroot);
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    void addView(String Num) {
        final LinearLayout ll = new LinearLayout(this);
        TextView t1 = new TextView(this);
        //String str = "상품명: " + OrderList.name + "   가격: " + OrderList.totalprice + "  주문수량: " + OrderList.quantity;
        //String str = "상품명: ";
        t1.setText(Num);
        t1.setTextSize(22);
        t1.setTextColor(Color.BLACK);
        ll.addView(t1);
        OrderView.addView(ll);

    }

    void upList(treenode tree) {

        final LinearLayout ll = new LinearLayout(this);
        TextView temp = new TextView(this);
        //String str = "상품명: " + OrderList.name + "   가격: " + OrderList.totalprice + "  주문수량: " + OrderList.quantity;
        //String str = "상품명: ";
        temp.setText(tree.data);
        temp.setTextSize(22);
        temp.setTextColor(Color.BLACK);
        final LinearLayout.LayoutParams ll2 = new LinearLayout.LayoutParams(120, 120);
        final Button btn = new Button(this);
        final Button btn2 = new Button(this);
        final String position = tree.DBroot+" "+tree.id;
        final treenode treeposition = tree;
        final String Position2 = tree.data;

        final LinearLayout ButtonLocation;
        btn.setId(count);
        count++;
        btn.setText("보기");

        btn2.setId(count);
        count++;
        btn2.setText("취소");
        //btn.setLayoutParams(ll2);

        ll.addView(temp);
        ll.addView(btn);
        ll.addView(btn2);

        OrderView.addView(ll);

        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                SetOreder = position;
                SetOreder2 = Position2;
                ShowPopUp();
            }

        });
        btn2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String[] root = position.split(" ");
                OrderView.removeView(ll);

                Database.child(root[0]).child(root[1]).child(root[2]).child(root[3]).setValue(null);
                listreset();

            }

        });

    }

    void ShowPopUp() {

        Database.addListenerForSingleValueEvent(new ValueEventListener() {
            String Message="";
            String temp;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                OrderData tmepdata;
                String[] root = SetOreder.split(" ");
                for (DataSnapshot snapshot : dataSnapshot.child(root[0]).child(root[1]).child(root[2]).child(root[3]).child(root[4]).getChildren()) {
                    tmepdata = snapshot.getValue(OrderData.class);
                    temp = "상품명: " + tmepdata.name + "   가격: " + tmepdata.totalprice + "  주문수량: " + tmepdata.quantity;
                    Message+=temp+'\n';
                    totalquantity += tmepdata.quantity;
                    totalfee += tmepdata.totalprice;
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

    void ReDrawView(int set){
        OrderView.removeAllViews();
        switch (set) {
            case 0:
                Collections.sort(tempArraydata_ALL);
                for (String i : tempArraydata_ALL) {
                    addView(i);

                }

                break;

            case 1:
                Collections.sort((tempArraydata_A));
                for (String i : tempArraydata_A) {
                    addView(i);


                }
                break;
            case 2:
                Collections.sort(tempArraydata_B);
                for (String i : tempArraydata_B) {
                    addView(i);
                }
                break;
            case 3:
                Collections.sort(tempArraydata_C);
                for (String i : tempArraydata_C) {
                    addView(i);
                }
                break;

            case 4:

                MyRedBlacktree tempOrdertree = Ordertree;

                treenode temptree = tempOrdertree.Treesearch(SearchView.getText().toString());
                if (temptree != null) {
                    upList(temptree);
                }else{
                    MesaageUp2("존재하지 않는 주문입니다.");
                }

                break;
        }
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
    void MesaageUp2(String str){
        // 다이얼로그 바디
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
        // 메세지
        alert_confirm.setMessage(str);
        // 확인 버튼 리스너
        alert_confirm.setPositiveButton("확인", null);
        // 다이얼로그 생성
        AlertDialog alert = alert_confirm.create();

        // 다이얼로그 타이틀
        alert.setTitle("입력오류");
        // 다이얼로그 보기
        alert.show();

    }
}
