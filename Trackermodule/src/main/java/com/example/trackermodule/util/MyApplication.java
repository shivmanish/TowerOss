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

    public void saveString(String iKey, String iValue) {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(iKey, iValue);
        prefsEditor.apply();
    }

    public String getString(String iKey) {
        if (iKey.equals("locale")) {
            return mPrefs.getString(iKey, "en");
        } else {
            return mPrefs.getString(iKey, "");
        }
    }

    public String getToken() {
        return getString("accessToken");
    }

    public String getBearerToken() {
        return toketkey;
    }

    public String getRefresh() {
        return getString("refreshToken");
    }

}
