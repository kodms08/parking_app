package com.example.car_parking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private EditText mem_name, mem_phone;
    private TextView randnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        ActionBar abc = getSupportActionBar();
        abc.setTitle("LOGIN");

        mem_name = (EditText)findViewById(R.id.mem_name);
        mem_phone = (EditText)findViewById(R.id.mem_phone);
        login = (Button)findViewById(R.id.mem_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = mem_name.getText().toString();
                int userPhone = Integer.parseInt(mem_phone.getText().toString());
                int random = SubActivity.a;
                randnum.setText(random);
                /*intent.putExtra("userName",userName);
                intent.putExtra("userPhone",userPhone);
                startActivity(intent);*/

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success){ //로그인 성공한 경우
                                String userName = jsonObject.getString("userName");
                                String userPhone = jsonObject.getString("userPhone");

                                Toast.makeText(getApplicationContext(),"complete login",Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("userName",userName);
                                intent.putExtra("userPhone",userPhone);
                                startActivity(intent); //주석해제해야함
                            } else{ //로그인 실패한 경우
                                Toast.makeText(getApplicationContext(),"fail sign",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userName, userPhone, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
                //new BackgroundTask().execute(); 2번 textview 출력
            }
        });
    }



    //2번 textview 출력
    /*class BackgroundTask extends AsyncTask<Void,Void,String> {
        String target;

        @Override
        protected void onPreExecute() {
            target = "http://ksy1203k.dothome.co.kr/car.php";
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
                URL url = new URL(target);//URL 객체 생성

                //URL을 이용해서 웹페이지에 연결하는 부분
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                //바이트단위 입력스트림 생성 소스는 httpURLConnection
                InputStream inputStream = httpURLConnection.getInputStream();

                //웹페이지 출력물을 버퍼로 받음 버퍼로 하면 속도가 더 빨라짐
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;

                //문자열 처리를 더 빠르게 하기 위해 StringBuilder클래스를 사용함
                StringBuilder stringBuilder = new StringBuilder();

                //한줄씩 읽어서 stringBuilder에 저장함
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");//stringBuilder에 넣어줌
                }

                //사용했던 것도 다 닫아줌
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();//trim은 앞뒤의 공백을 제거함

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            Intent intent = new Intent(LoginActivity.this, getCarActivity.class);
            intent.putExtra("userList", result);//파싱한 값을 넘겨줌
            startActivity(intent);//getCarActivity 넘어감

        }
    }*/
}
