package com.direct.success.markdirect.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.direct.success.markdirect.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {


    private static final long SPLASH_SCREEN_DELAY = 1500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                Intent mainIntent = new Intent().setClass(
                        SplashActivity.this, LoginActivity.class);

                startActivity(mainIntent);


                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);


    }
}