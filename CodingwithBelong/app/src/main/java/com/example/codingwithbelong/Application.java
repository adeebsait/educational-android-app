package com.example.codingwithbelong;

import android.util.Log;

import com.example.codingwithbelong.util.ScreenTimeManager;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        Log.d("myappdata", "onCreate()");
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        Log.d("myappdata", "onTerminate()");
        super.onTerminate();
    }
}
