package com.example.car_parking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Reserv_Activity extends AppCompatActivity {

    Button btn_ikea;
    Button btn_homeplus;
    Button btn_emart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserv);

        //dialog
        btn_ikea = (Button) findViewById(R.id.btn_ikea);
        btn_homeplus = (Button) findViewById(R.id.btn_homeplus);
        btn_emart = (Button) findViewById(R.id.btn_emart);


        btn_ikea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//이케아
                Intent intent = new Intent(getApplicationContext(), SubActivity.class); //다음 화면으로 넘어갈 클래스 지정
                startActivity(intent); //다음 화면으로 넘어간다
                Toast.makeText(Reserv_Activity.this, "Complete IKEA", Toast.LENGTH_SHORT).show();
            }
        });

        btn_homeplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//이케아
                Intent intent = new Intent(getApplicationContext(), SubActivity1.class); //다음 화면으로 넘어갈 클래스 지정
                startActivity(intent); //다음 화면으로 넘어간다
                Toast.makeText(Reserv_Activity.this, "Complete HOMEPLUS", Toast.LENGTH_SHORT).show();
            }
        });

        btn_emart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//이케아
                Intent intent = new Intent(getApplicationContext(), SubActivity2.class); //다음 화면으로 넘어갈 클래스 지정
                startActivity(intent); //다음 화면으로 넘어간다
                Toast.makeText(Reserv_Activity.this, "Complete EMART", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
