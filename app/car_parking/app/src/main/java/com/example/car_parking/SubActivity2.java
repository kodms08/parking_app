package com.example.car_parking;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SubActivity2 extends AppCompatActivity { //이마트

    Button btn_dialog9;
    Button btn_dialog10;
    Button btn_dialog11;
    TextView TextView;

    private Socket socket2;
    private thread2 thread2;
    private InputStream IS2;
    private PrintWriter OS2;
    private int port = 8888;
    private String SERVER_IP = "172.20.10.7";
    int sensor9, sensor10;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            socket2 = new Socket(SERVER_IP, port);
            OS2 = new PrintWriter(socket2.getOutputStream(),true);
            Log.d("socket","socket");
        }catch(Exception e){
            e.printStackTrace();
        }
        thread2 = new thread2();
        thread2.start();
        OS2.println("connect success");
        Log.d("connect success","connect success");

        btn_dialog9=(Button)findViewById(R.id.btn_10);
        btn_dialog10=(Button)findViewById(R.id.btn_11);
        btn_dialog11=(Button)findViewById(R.id.btn_12);

        if (sensor9 >= 100) { //예약 불가(이미 차가 존재해서)
            btn_dialog9.setBackgroundColor(Color.RED);
            btn_dialog9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    Toast.makeText( SubActivity2.this, "WARNING : A car already exists.", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if ((sensor9 > 10) && (sensor9 < 30)) { //예약 세모
            btn_dialog9.setBackgroundColor(Color.BLUE);
            btn_dialog9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    Toast.makeText(SubActivity2.this, "Wait a Moment", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            btn_dialog9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    //ExampleDialog exampleDialog = new ExampleDialog();
                    //exampleDialog.show(getSupportFragmentManager(), "example dialog");
                }
            });
        }
        if (sensor10 >= 100) { //예약 불가(이미 차가 존재해서)
            btn_dialog10.setBackgroundColor(Color.RED);
            btn_dialog10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    Toast.makeText( SubActivity2.this, "WARNING : A car already exists.", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if ((sensor10 > 10) && (sensor10 < 30)) { //예약 세모
            btn_dialog10.setBackgroundColor(Color.BLUE);
            btn_dialog10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    Toast.makeText(SubActivity2.this, "Wait a Moment", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            btn_dialog10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    //ExampleDialog exampleDialog = new ExampleDialog();
                    //exampleDialog.show(getSupportFragmentManager(), "example dialog");
                }
            });
        }
    }


    class thread2 extends Thread
    {
        @Override
        public void run() {
            byte[] buf = new byte[1024];
            String data = null;
            while(true)
            {
                try {
                    IS2 = socket2.getInputStream();
                    DataInputStream DIS = new DataInputStream(IS2);
                    DIS.read(buf,0,1024);
                    final String redata = new String(buf,0,1024);
                    if(data != redata)
                    {
                        TextView.setText(redata);
                    }
                    Log.d("data","data : " + redata);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (redata.contains("hello")) { // 서버가 보낸 메시지에 Normal 이 포함되어 있으면
                                btn_dialog11.setBackgroundColor(Color.RED);
                                btn_dialog11.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                                        Toast.makeText(SubActivity2.this, "WARNING : A car already exists.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else if (redata.contains("bye")) {
                                btn_dialog11.setBackgroundColor(Color.BLUE);
                                btn_dialog11.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                                        Toast.makeText(SubActivity2.this, "Wait a Moment", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else if (redata.contains("Wait")) {
                                btn_dialog11.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                                        final EditText carnumber = new EditText(SubActivity2.this);
                                        AlertDialog.Builder builder = new AlertDialog.Builder(SubActivity2.this);

                                        builder.setTitle("Reservation");
                                        builder.setMessage("예약할 차 번호를 입력하세요");
                                        builder.setView(carnumber);
                                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Log.d("dialog","dialog");
                                                String CAR = carnumber.getText().toString();
                                                OS2.println(CAR);
                                            }
                                        });
                                        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Log.d("cancel","cancel");
                                                dialogInterface.dismiss();
                                            }
                                        });
                                        builder.show();
                                    }
                                });
                            }
                        }
                    });
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        try {
            socket2.close(); //소켓을 닫는다.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


