package com.example.flyaudioqrcode.base;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

public class BaseApplication extends Application {
    private static Context sApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        sApplicationContext = getApplicationContext();
    }

    public static Context getContext(){
        return sApplicationContext;
    }
}
