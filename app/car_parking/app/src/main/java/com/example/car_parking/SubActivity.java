package com.example.car_parking;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;


public class SubActivity extends AppCompatActivity{

    Button btn_dialog,btn_dialog1,btn_dialog2,btn_dialog3,btn_dialog4,btn_dialog5;

    private Socket socket;
    private thread thread;
    private InputStream IS;
    private PrintWriter OS;
    private BufferedWriter networkWriter;
    private int port = 8888;
    private String SERVER_IP = "172.20.10.7";
    int sensor, sensor1, sensor2, sensor3, sensor4;
    //String url = "http://ksy1203k.dothome.co.kr/getuserCar.php";
    public static int a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            socket = new Socket(SERVER_IP, port);
            OS = new PrintWriter(socket.getOutputStream(),true);
            //networkWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //OS = new PrintWriter(networkWriter,true);
            Log.d("socket","socket");
        }catch(Exception e){
            e.printStackTrace();
        }
        thread = new thread();
        thread.start();
        OS.println("connect");
        Log.d("connect success","connect success");

        ActionBar ab = getSupportActionBar();
        ab.setTitle("IKEA");

        //dialog
        btn_dialog = (Button) findViewById(R.id.btn_dialog);
        btn_dialog1 = (Button) findViewById(R.id.btn_1);
        btn_dialog2 = (Button) findViewById(R.id.btn_2);
        btn_dialog3 = (Button) findViewById(R.id.btn_3);
        btn_dialog4 = (Button) findViewById(R.id.btn_4);
        btn_dialog5 = (Button) findViewById(R.id.btn_5);

        sensor = 101;
        sensor1 = 12;
        sensor2 = 22;
        sensor3 = 133;
        sensor4 = 190;

        if (sensor >= 100) { //예약 불가(이미 차가 존재해서)
            btn_dialog.setBackgroundColor(Color.RED);
            btn_dialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    Toast.makeText( SubActivity.this, "WARNING : A car already exists.", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if ((sensor > 10) && (sensor < 30)) { //예약 세모
            btn_dialog.setBackgroundColor(Color.BLUE);
            btn_dialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    Toast.makeText(SubActivity.this, "Wait a Moment", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            btn_dialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    //ExampleDialog exampleDialog = new ExampleDialog();
                    //exampleDialog.show(getSupportFragmentManager(), "example dialog");
                }
            });
        }

        if (sensor1 >= 100) { //예약 불가(이미 차가 존재해서)
            btn_dialog1.setBackgroundColor(Color.RED);
            btn_dialog1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    Toast.makeText(SubActivity.this, "WARNING : A car already exists.", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if ((sensor1 > 10) && (sensor1 < 30)) { //예약 세모
            btn_dialog1.setBackgroundColor(Color.BLUE);
            btn_dialog1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    Toast.makeText(SubActivity.this, "Wait a Moment", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            btn_dialog1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    //ExampleDialog exampleDialog = new ExampleDialog();
                    //exampleDialog.show(getSupportFragmentManager(), "example dialog");
                }
            });
        }

        if (sensor2 >= 100) { //예약 불가(이미 차가 존재해서)
            btn_dialog2.setBackgroundColor(Color.RED);
            btn_dialog2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    Toast.makeText(SubActivity.this, "WARNING : A car already exists.", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if ((sensor2 > 10) && (sensor2 < 30)) { //예약 세모
            btn_dialog2.setBackgroundColor(Color.BLUE);
            btn_dialog2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    Toast.makeText(SubActivity.this, "Wait a Moment", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            btn_dialog2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    //ExampleDialog exampleDialog = new ExampleDialog();
                    //exampleDialog.show(getSupportFragmentManager(), "example dialog");
                }
            });
        }

        if (sensor3 >= 100) { //예약 불가(이미 차가 존재해서)
            btn_dialog3.setBackgroundColor(Color.RED);
            btn_dialog3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    Toast.makeText(SubActivity.this, "WARNING : A car already exists.", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if ((sensor3 > 10) && (sensor3 < 30)) { //예약 세모
            btn_dialog3.setBackgroundColor(Color.BLUE);
            btn_dialog3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    Toast.makeText(SubActivity.this, "Wait a Moment", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            btn_dialog3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    //ExampleDialog exampleDialog = new ExampleDialog();
                    //exampleDialog.show(getSupportFragmentManager(), "example dialog");
                }
            });
        }

        if (sensor4 >= 100) { //예약 불가(이미 차가 존재해서)
            btn_dialog4.setBackgroundColor(Color.RED);
            btn_dialog4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    Toast.makeText(SubActivity.this, "WARNING : A car already exists.", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if ((sensor4 > 10) && (sensor4 < 30)) { //예약 세모
            btn_dialog4.setBackgroundColor(Color.BLUE);
            btn_dialog4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(SubActivity.this, "Wait a Moment", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            btn_dialog4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                    //ExampleDialog exampleDialog = new ExampleDialog();
                    //exampleDialog.show(getSupportFragmentManager(), "example dialog");
                }
            });
        }
    }


    class thread extends Thread
    {
        @Override
        public void run() {
            byte[] buf = new byte[5];
            while(true)
            {
                try {
                    IS = socket.getInputStream();
                    DataInputStream DIS = new DataInputStream(IS);
                    DIS.read(buf,0,5);
                    Log.d("data1","data1");
                    final String rdata = new String(buf,0,5);
                    /*if(null != rdata)
                    {
                        *//*TextView.setText(null);
                        TextView.setText(rdata);*//*
                    }*/
                    Log.d("data","data : " + rdata);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (rdata.contains("wait")) {
                                btn_dialog5.setBackgroundColor(Color.BLUE);
                                btn_dialog5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Toast.makeText(SubActivity.this, "Wait a Moment", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else if (rdata.contains("empty")) {
                                btn_dialog5.setBackgroundColor(Color.GREEN);
                                Log.d("dialog1","dialog1");
                                btn_dialog5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                                        final EditText carnumber = new EditText(SubActivity.this);
                                        Log.d("dialog2","dialog2");

                                        //Dialog1
                                        AlertDialog.Builder builder = new AlertDialog.Builder(SubActivity.this);
                                        builder.setTitle("Reservation");
                                        builder.setMessage("예약할 차 번호를 입력하세요");
                                        builder.setView(carnumber);
                                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Log.d("dialog","dialog");
                                                String CAR = carnumber.getText().toString();
                                                OS.println(CAR);
//                                                btn_dialog5.setBackgroundColor(Color.RED); //다이얼로그 안 뜨게 하기
//                                                btn_dialog5.setOnClickListener(new View.OnClickListener() {
//                                                    @Override
//                                                    public void onClick(View view) {
//                                                        Toast.makeText(SubActivity.this, "Wait a Moment", Toast.LENGTH_SHORT).show();
//                                                    }
//                                                });
                                            }
                                        });
                                        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Log.d("cancel","cancel");
                                                dialogInterface.dismiss();
                                            }
                                        });
                                        builder.show(); //Dialog1

                                        //Dialog 대신 바로 차번호 전송
                                        //gPHP = new GettingPHP();
                                        //gPHP.execute(url);
                                        //OS.println(gPHP);

                                        /*Intent intent = getIntent();
                                        String userName = intent.getExtras().getString("userName");
                                        int userPhone = intent.getExtras().getInt("userPhone");
                                        TextView1.setText(userName);*/
                                    }
                                });
                            }
                            else {
                                btn_dialog5.setBackgroundColor(Color.RED);
                                btn_dialog5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) { //btn_dialog button 눌렀을 때 dialog 띄우는 함수
                                        Toast.makeText(SubActivity.this, "WARNING : A car already exists.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            //TextView.setText(null);
                        }
                    });
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }


    /*class GettingPHP extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            StringBuilder jsonHtml = new StringBuilder();
            try {
                URL phpUrl = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection)phpUrl.openConnection();

                if ( conn != null ) {
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);

                    if ( conn.getResponseCode() == HttpURLConnection.HTTP_OK ) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                        while ( true ) {
                            String line = br.readLine();
                            if ( line == null )
                                break;
                            jsonHtml.append(line + "\n");
                        }
                        br.close();
                    }
                    conn.disconnect();
                }
            } catch ( Exception e ) {
                e.printStackTrace();
            }
            return jsonHtml.toString();
        }

        protected void onPostExecute(String str) {
            try {
                // PHP에서 받아온 JSON 데이터를 JSON오브젝트로 변환
                JSONObject jObject = new JSONObject(str);
                // results라는 key는 JSON배열로 되어있다.
                JSONArray results = jObject.getJSONArray("results");
                String zz = "";
                zz += "userCar : " + jObject.get("userCar");

           *//*     for ( int i = 0; i < results.length(); ++i ) {
                    JSONObject temp = results.getJSONObject(i);
                    zz += "\tdoc_idx : " + temp.get("doc_idx");
                    zz += "\tmember_idx : " + temp.get("member_idx");
                    zz += "\tsubject : " + temp.get("subject");
                    zz += "\tcontent : " + temp.get("content");
                    zz += "\treg_date : " + temp.get("reg_date");
                    zz += "\n\t--------------------------------------------\n";
                } *//*
                //TextView1.setText(zz);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }*/
/*    public void clickLoad(View view){
        String serverUrl = "http://ksy1203k.dothome.co.kr/json.php";
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
            //volley 라이브러리의 GET방식은 버튼 누를때마다 새로운 갱신 데이터를 불러들이지 않음. 그래서 POST 방식 사용
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(SubActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                //파라미터로 응답받은 결과 JsonArray를 분석

                items.clear();
                adapter.notifyDataSetChanged();
                try {

                    for(int i=0;i<response.length();i++){
                        JSONObject jsonObject= response.getJSONObject(i);

                        int phone= Integer.parseInt(jsonObject.getString("phone")); //no가 문자열이라서 바꿔야함.
                        String name=jsonObject.getString("name");
                        String car=jsonObject.getString("car");
                        //String imgPath=jsonObject.getString("imgPath");
                        //String date=jsonObject.getString("date");

                        //이미지 경로의 경우 서버 IP가 제외된 주소이므로(uploads/xxxx.jpg) 바로 사용 불가.
                        //imgPath = "http://umul.dothome.co.kr/Android/"+imgPath;

                        items.add(0,new Item(name,phone,car)); // 첫 번째 매개변수는 몇번째에 추가 될지, 제일 위에 오도록
                        adapter.notifyItemInserted(0);
                    }
                } catch (JSONException e) {e.printStackTrace();}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SubActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

        //실제 요청 작업을 수행해주는 요청큐 객체 생성
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        //요청큐에 요청 객체 생성
        requestQueue.add(jsonArrayRequest);
    }*/
}

