package com.example.car_parking;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SubActivity1 extends AppCompatActivity { //홈플러스

    Button btn_dialog6;
    Button btn_dialog7;
    Button btn_dialog8;
    TextView TextView;

    private Socket socket1;
    private thread1 thread1;
    private InputStream IS1;
    private PrintWriter OS1;
    private int port = 8888;
    private String SERVER_IP = "172.20.10.7";
    int sensor6, sensor7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            socket1 = new Socket(SERVER_IP, port);
            OS1 = new PrintWriter(socket1.getOutputStream(), true);
            Log.d("socket", "socket");
        } catch (Exception e) {
            e.printStackTrace();
        }
        thread1 = new thread1();
        thread1.start();
        OS1.println("connect success");
        Log.d("connect success", "connect success");

        btn_dialog6 = (Button) findViewById(R.id.btn_7);
        btn_dialog7 = (Button) findViewById(R.id.btn_8);
        btn_dialog8 = (Button) findViewById(R.id.btn_9);

        sensor6 = 101;
        sensor7 = 23;

        if (sensor6 >= 100) { //예약 불가(이미 차가 존재해서)
            btn_dialog6.setBackgroundColor(Color.RED);
            btn_dialog6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    Toast.makeText( SubActivity1.this, "WARNING : A car already exists.", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if ((sensor6 > 10) && (sensor6 < 30)) { //예약 세모
            btn_dialog6.setBackgroundColor(Color.BLUE);
            btn_dialog6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    Toast.makeText(SubActivity1.this, "Wait a Moment", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            btn_dialog6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    //ExampleDialog exampleDialog = new ExampleDialog();
                    //exampleDialog.show(getSupportFragmentManager(), "example dialog");
                }
            });
        }
        if (sensor7 >= 100) { //예약 불가(이미 차가 존재해서)
            btn_dialog7.setBackgroundColor(Color.RED);
            btn_dialog7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    Toast.makeText( SubActivity1.this, "WARNING : A car already exists.", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if ((sensor7 > 10) && (sensor7 < 30)) { //예약 세모
            btn_dialog7.setBackgroundColor(Color.BLUE);
            btn_dialog7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    Toast.makeText(SubActivity1.this, "Wait a Moment", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            btn_dialog7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    //ExampleDialog exampleDialog = new ExampleDialog();
                    //exampleDialog.show(getSupportFragmentManager(), "example dialog");
                }
            });
        }
    }

    class thread1 extends Thread
    {
        @Override
        public void run() {
            byte[] buf = new byte[1024];
            String data = null;
            while(true)
            {
                try {
                    IS1 = socket1.getInputStream();
                    DataInputStream DIS = new DataInputStream(IS1);
                    DIS.read(buf,0,1024);
                    final String redata = new String(buf,0,1024);
                    if(data != redata)
                    {
                        TextView.setText(redata);
                    }
                    Log.d("data","received data : " + redata);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (redata.contains("hello")) {
                                btn_dialog8.setBackgroundColor(Color.RED);
                                btn_dialog8.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                                        Toast.makeText(SubActivity1.this, "WARNING : A car already exists.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else if (redata.contains("bye")) {
                                btn_dialog8.setBackgroundColor(Color.BLUE);
                                btn_dialog8.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                                        Toast.makeText(SubActivity1.this, "Wait a Moment", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else if (redata.contains("Wait")) {
                                btn_dialog8.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                                        final EditText carnumber = new EditText(SubActivity1.this);
                                        AlertDialog.Builder builder = new AlertDialog.Builder(SubActivity1.this);

                                        builder.setTitle("Reservation");
                                        builder.setMessage("예약할 차 번호를 입력하세요");
                                        builder.setView(carnumber);
                                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Log.d("dialog","dialog");
                                                String CAR = carnumber.getText().toString();
                                                OS1.println(CAR);
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
            socket1.close(); //소켓을 닫는다.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


