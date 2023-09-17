package com.example.car_parking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignActivity extends AppCompatActivity {

    Button home;
    Button sign_up;

    private EditText name, phone, car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sign);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("SIGN UP");
        //아이디 값 찾아주기
        name = (EditText)findViewById(R.id.name);
        phone = (EditText)findViewById(R.id.phone);
        car = (EditText)findViewById(R.id.car);
        sign_up = (Button)findViewById(R.id.sign_up);
        home = (Button)findViewById(R.id.home);

        //회원가입 버튼 클릭시 수행
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //EditText 현재 입력되어있는 값을 가져옴
                String userName = name.getText().toString();
                int userPhone = Integer.parseInt(phone.getText().toString());
                String userCar = car.getText().toString();
                Log.d("success","success");

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       try {
                           Log.d("success1","success1");
                           JSONObject jsonObject = new JSONObject(response);
                           boolean success = jsonObject.getBoolean("success");
                           if(success){ //회원가입 성공한 경우
                               Log.d("success2","success2");
                               Toast.makeText(getApplicationContext(),"complete sign",Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(SignActivity.this, LoginActivity.class);
                               startActivity(intent);
                           } else{ //회원가입 실패한 경우
                               Toast.makeText(getApplicationContext(),"fail sign",Toast.LENGTH_SHORT).show();
                               return;
                           }
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                   }
                };

                //서버로 Volley를 이용해서 요청 함
                SignRequest signRequest = new SignRequest(userName, userPhone, userCar, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SignActivity.this);
                queue.add(signRequest);
                Log.d("success3","success3");
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class); //다음 화면으로 넘어갈 클래스 지정
                startActivity(intent); //다음 화면으로 넘어간다
            }
        });
    }
}
