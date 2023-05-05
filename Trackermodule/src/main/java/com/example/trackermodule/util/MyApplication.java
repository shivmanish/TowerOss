package com.example.trackermodule.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.stetho.Stetho;

public class MyApplication extends Application {
    public Boolean isTaskEditable = false;
    SharedPreferences mPrefs;
    public String toketkey = "";

    static MyApplication mInstance;

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Stetho.initializeWithDefaults(this);
        mPrefs = getSharedPreferences("TowerOss-Preferences", Context.MODE_PRIVATE);
    }



}
