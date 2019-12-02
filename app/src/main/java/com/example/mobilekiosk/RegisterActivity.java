 package com.example.mobilekiosk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

 public class RegisterActivity extends AppCompatActivity {

    private EditText et_id, et_pass, et_name, et_number, et_card;

    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        et_name = findViewById(R.id.et_name);
        et_number = findViewById(R.id.et_number);
        et_card = findViewById(R.id.et_card);

        btn_register = findViewById((R.id.btn_register));
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = et_id.getText().toString();
                String userPassword = et_pass.getText().toString();
                String userName = et_name.getText().toString();
                String userNumber =  et_number.getText().toString();
                String userCard = et_card.getText().toString();
                String hashpwd;

                if( CheckNumber(userNumber) == false || CheckNumber(userCard) == false){
                    Toast.makeText(getApplicationContext(),"학번 또는 카드번호가 숫자가 아닙니다.",Toast.LENGTH_SHORT).show();
                    return;
                }

                Response.Listener<String>  responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success){
                                Toast.makeText(getApplicationContext(),"회원등록 성공",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"회원등록 실패",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                try {
                        hashpwd = bytesToHex1(sha256(userPassword));
                        RegisterRequest registerRequest = new RegisterRequest(userID,hashpwd,userName,userNumber,userCard, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                        queue.add(registerRequest);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });
    }
     public static byte[] sha256(String msg) throws NoSuchAlgorithmException {
         MessageDigest md = MessageDigest.getInstance("SHA-256");
         md.update(msg.getBytes());

         return md.digest();
     }
     public static String bytesToHex1(byte[] bytes) {
         StringBuffer sb = new StringBuffer();
         for (byte b : bytes) {
             sb.append(String.format("%02x", b));
         }
         return sb.toString();
     }
     public boolean CheckNumber(String str){
        char check;

        if(str.equals(""))
            return false;
        for( int i = 0 ; i < str.length() ; i ++){
            check = str.charAt(i);
            if( check < 48 || check > 58){
                return false;
            }
        }
        return true;
     }
}
