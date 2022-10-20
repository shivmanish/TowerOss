package com.smarthub.baseapplication.utils;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

public class AppController extends Application {

    static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }
}
