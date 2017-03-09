package com.direct.success.markdirect.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.direct.success.markdirect.R;
import com.direct.success.markdirect.managers.RegisterUserAPI;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static android.R.attr.id;

public class LoginActivity extends AppCompatActivity {
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private Button registerButton;
    private Button enterButton;
    private String email;
    private String birthday;
    private String gender;
    private String tokenFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_login_activity);*/

        setContentView(R.layout.activity_login);

        SharedPreferences prefs =
                getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
        String token = prefs.getString("Token", null);

        if (token!=null){
            Intent i = new Intent(LoginActivity.this, GeneralActivity.class);
            startActivity(i);

        }

        registerButton = (Button) findViewById(R.id.activity_login___registerButton);

        enterButton = (Button) findViewById(R.id.enter_button);

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends"));

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //consulta a la api

                String token ="";
                SharedPreferences prefs =
                        getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("Token", token);
                editor.putString("Tipo", "MANUAL");
                editor.commit();

                Intent intent = new Intent(LoginActivity.this, GeneralActivity.class);
                startActivity(intent);

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        /*loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("Conectado",loginResult.getAccessToken().getUserId());
                Log.d("tokeeeeeeen", loginResult.getAccessToken().getToken());

            }

            @Override
            public void onCancel() {
                Log.d("Cancelado", "ouchhhhh");
            }


            @Override
            public void onError(FacebookException error) {
                Log.d("Error", "errorrrrrr");

            }
        });*/




        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                tokenFacebook = loginResult.getAccessToken().getToken().toString();
                Log.d("", "success");
                Log.d("tokeeeeeeen", loginResult.getAccessToken().getToken().toString());
                SharedPreferences prefs =
                        getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("Token", loginResult.getAccessToken().getToken().toString());
                editor.putString("Tipo", "FACEBOOK");
                editor.commit();
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("LoginActivity", response.toString());

                                // Application code
                                try {
                                    email = object.getString("email");
                                    Log.d("maillll", email);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {//TODO:convertir a edad
                                    String birthday = object.getString("birthday"); // 01/31/1980 format
                                    Log.d("cumpleeeee", birthday);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try{
                                    gender = object.getString("gender");
                                    Log.d("gender", gender);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();

                Log.d("respoooonse",request.toString());
            }

            @Override
            public void onCancel() {
                Log.d("", "cancel");

            }

            @Override
            public void onError(FacebookException error) {
                Log.d("", "error");

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
            if(resultCode==-1)
            {
                Date dateOfBirth = new Date(birthday);
                int age = 0;
                Calendar born = Calendar.getInstance();
                Calendar now = Calendar.getInstance();
                if(dateOfBirth!= null) {
                    now.setTime(new Date());
                    born.setTime(dateOfBirth);
                    if(born.after(now)) {
                        throw new IllegalArgumentException("Can't be born in the future");
                    }
                    age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
                    if(now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR))  {
                        age-=1;
                    }
                }
                birthday=""+age;
                Log.d("birthday",birthday);

                RegisterUserAPI registerUserAPI = new RegisterUserAPI();
                registerUserAPI.sendPost(getBaseContext(), email, "",birthday,gender, "FACEBOOK");
                registerUserAPI.setListener(new RegisterUserAPI.RegisterUserAPINewTokenListener() {
                    @Override
                    public void onNewToken(String token) {
                        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("Token", tokenFacebook);
                        editor.putString("Tipo", "FACEBOOK");
                        editor.commit();
                        Intent i = new Intent(LoginActivity.this, GeneralActivity.class);
                        startActivity(i);

                    }
                });
                Intent i = new Intent(LoginActivity.this, GeneralActivity.class);
                i.putExtra("id",id);
                startActivity(i);
            }

    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
