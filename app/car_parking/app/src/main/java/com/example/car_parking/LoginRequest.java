package com.example.car_parking;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    //서버 URL 설정(PHP 파일 연동)
    final static private String URL = "http://ksy1203k.dothome.co.kr/Login.php";
    private Map<String,String> map;


    public LoginRequest(String userName, int userPhone, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("userName", userName);
        map.put("userPhone", userPhone+""); //PRIMARY KEY
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}
