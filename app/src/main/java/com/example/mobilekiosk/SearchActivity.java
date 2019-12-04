package com.example.mobilekiosk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private List<Store> list;
    private ListView listView;
    private EditText editSearch;
    private ListViewAdapter adapter;
    private ArrayList<Store> arraylist;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editSearch = (EditText) findViewById(R.id.editSearch);
        listView = (ListView) findViewById(R.id.listView);
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");

        // 리스트를 생성한다.
        list = new ArrayList<Store>();

        // 검색에 사용할 데이터을 미리 저장한다.
        settingList();

        // 리스트의 모든 데이터를 arraylist에 복사한다.// list 복사본을 만든다.
        arraylist = new ArrayList<Store>();
        arraylist.addAll(list);

        // 리스트에 연동될 아답터를 생성한다.
        adapter = new ListViewAdapter(list, this);

        // 리스트뷰에 아답터를 연결한다.
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(listener);

        // input창에 검색어를 입력시 리스너
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editSearch.getText().toString();
                search(text);
            }
        });


    }

    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Store store = list.get(position);
            if (store.name.equals("봉구스밥버거")) {
                Intent intent = new Intent(SearchActivity.this, Order_Menu.class);
                intent.putExtra("userID",userID);
                startActivity(intent);
            }
            if (store.name.equals("호접몽")) {
                Intent intent = new Intent(SearchActivity.this, Order_Hojupmong.class);
                intent.putExtra("userID",userID);
                startActivity(intent);
            }
            if (store.name.equals("종로빈대떡")) {
                Intent intent = new Intent(SearchActivity.this, Order_Jongro.class);
                intent.putExtra("userID",userID);
                startActivity(intent);
            }
        }
    };

    // 검색을 수행하는 메소드
    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        list.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            list.addAll(arraylist);
        }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다. 순차탐색
            for(int i = 0;i < arraylist.size(); i++)
            {
                // 입력받은 단어가 가게이름 or 카테고리 이름 or 메뉴 이름이면
                if (arraylist.get(i).name.toLowerCase().contains(charText) || arraylist.get(i).category.toLowerCase().contains(charText)
                        || arraylist.get(i).menuList[0].toLowerCase().contains(charText)
                        || arraylist.get(i).menuList[1].toLowerCase().contains(charText)
                        || arraylist.get(i).menuList[2].toLowerCase().contains(charText)
                        || arraylist.get(i).menuList[3].toLowerCase().contains(charText)
                        || arraylist.get(i).menuList[4].toLowerCase().contains(charText)
                        || arraylist.get(i).menuList[5].toLowerCase().contains(charText))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    list.add(arraylist.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }

    // 검색에 사용될 데이터를 리스트에 추가한다.
    private void settingList(){
        String bongList[] = new String[6]; // 봉구스 밥버거 메뉴
        String hoList[] = new String[6]; // 호접몽 메뉴
        String jongList[] = new String[6]; // 종빈 메뉴

        bongList[0] = "햄쏘야 밥버거";
        bongList[1] = "햄치즈쏘야 밥버거";
        bongList[2] = "에그김치제육 밥버거";
        bongList[3] = "치즈맛있새우 밥버거";
        bongList[4] = "에그치즈닭갈비 밥버거";
        bongList[5] = "마요오므라이스 밥버거";

        hoList[0] = "짬뽕";
        hoList[1] = "짬뽕";
        hoList[2] = "볶음밥";
        hoList[3] = "사천탕면";
        hoList[4] = "잡채밥";
        hoList[5] = "탕수육";

        jongList[0] = "김치빈대떡";
        jongList[1] = "고기빈대떡";
        jongList[2] = "해물빈대떡";
        jongList[3] = "굴빈대떡";
        jongList[4] = "낙지빈대떡";
        jongList[5] = "해물파전";


        list.add(new Store("봉구스밥버거","한식",bongList));
        list.add(new Store("호접몽","중식",hoList));
        list.add(new Store("종로빈대떡","한식",jongList));
    }
}
