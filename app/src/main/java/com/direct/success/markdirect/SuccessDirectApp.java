package com.direct.success.markdirect;

import android.app.Application;
import android.util.Log;

import io.realm.Realm;

public class SuccessDirectApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Application","Creating application");
        Realm.init(this);//Init Realm
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d("Application","Low memory detected");
    }
}
