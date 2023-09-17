package com.example.car_parking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity { //이케아

    private static final String TAG = "";
    Button btn_login;
    Button btn_sign;
    Button btn_reserv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //dialog
        btn_login = (Button) findViewById(R.id.login);
        btn_sign = (Button) findViewById(R.id.sign);
        btn_reserv = (Button) findViewById(R.id.reservation);


        btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class); //다음 화면으로 넘어갈 클래스 지정
                    startActivity(intent); //다음 화면으로 넘어간다
                }
            });

        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignActivity.class); //다음 화면으로 넘어갈 클래스 지정
                startActivity(intent); //다음 화면으로 넘어간다
            }
        });

        btn_reserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Reserv_Activity.class); //다음 화면으로 넘어갈 클래스 지정
                startActivity(intent); //다음 화면으로 넘어간다
            }
        });
    }

}

