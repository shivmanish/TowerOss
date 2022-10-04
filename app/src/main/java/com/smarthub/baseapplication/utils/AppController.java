package com.smarthub.baseapplication.utils;

import android.app.Application;

public class AppController extends Application {

    static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }
}
