package com.direct.success.markdirect.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.direct.success.markdirect.R;
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

import static android.R.attr.id;

public class LoginActivity extends AppCompatActivity {
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
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
        });




        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("", "success");
                Log.d("tokeeeeeeen", loginResult.getAccessToken().getToken().toString());

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("LoginActivity", response.toString());

                                // Application code
                                try {
                                    String email = object.getString("email");
                                    Log.d("maillll", email);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    String birthday = object.getString("birthday"); // 01/31/1980 format
                                    Log.d("cumpleeeee", birthday);
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

            Intent i = new Intent(LoginActivity.this, GeneralActivity.class);
            i.putExtra("id",id);
            startActivity(i);


    }
}
