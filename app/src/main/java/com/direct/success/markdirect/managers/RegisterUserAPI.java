package com.direct.success.markdirect.managers;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class RegisterUserAPI {
    public RegisterUserAPI() {
    }
    public interface RegisterUserAPINewTokenListener{
        public void onNewToken(String token);
    }

    private RegisterUserAPINewTokenListener listener;

    public void setListener(RegisterUserAPINewTokenListener listener) {
        this.listener = listener;
    }

    //TODO poner URL;
    private static final String BASEURL="";
    public void sendPost(Context context, String mail, String pass, int age1, String sex1){
        String URL = BASEURL;
        final String email = mail;
        final String password = pass;
        final String age = ""+age1;
        final String sex = sex1;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("REGISTER_USER", "Todo ha ido bien");
                parseJSON(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("REGISTER_USER", "Algo ha ido mal" + error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                //params.put("Content-Type","application/json");
                params.put("email", email);
                params.put("password", password);
                params.put("age", age);
                params.put("sex", sex);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
    private void parseJSON(String response){
        Reader reader= new StringReader(response);
        Gson gson = new GsonBuilder().create();
        String token = gson.fromJson(reader, String.class);

        if(listener !=null){
            listener.onNewToken(token);
        }
    }
}
