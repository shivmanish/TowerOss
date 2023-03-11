package com.example.trackermodule.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.stetho.Stetho;

public class MyApplication extends Application {

    SharedPreferences mPrefs;
    public String toketkey = "";

    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        mPrefs = getSharedPreferences("TowerOss-Preferences", Context.MODE_PRIVATE);
    }



}
