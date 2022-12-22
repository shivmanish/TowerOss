package com.smarthub.baseapplication.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smarthub.baseapplication.utils.AppController;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

public class AppPreferences {
public static String DROPDOWNDATA = "dropdowndata";
    private static AppPreferences mInstance = null;
    private final SharedPreferences mPrefs;

    private AppPreferences() {
        mPrefs = AppController.getInstance().getSharedPreferences("TowerOss-Preferences", Context.MODE_PRIVATE);
    }

    public static synchronized AppPreferences getInstance() {
        if (mInstance == null) {
            mInstance = new AppPreferences();
        }
        return mInstance;
    }

    public void saveBoolean(String iKey, boolean iValue) {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putBoolean(iKey, iValue);
        prefsEditor.apply();
    }

    public void saveInteger(String iKey, int iValue) {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putInt(iKey, iValue);
        prefsEditor.apply();
    }

    public void saveLong(String iKey, Long iValue) {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putLong(iKey, iValue);
        prefsEditor.apply();
    }

    public void saveString(String iKey, String iValue) {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(iKey, iValue);
        prefsEditor.apply();
    }
    public boolean getBoolean(String iKey) {
        return mPrefs.getBoolean(iKey, false);
    }

    public int getInteger(String iKey) {
        return mPrefs.getInt(iKey, 0);
    }

    public Long getLong(String iKey) {
        return mPrefs.getLong(iKey, 0L);
    }

    public String getString(String iKey) {
        if (iKey.equals("locale")) {
            return mPrefs.getString(iKey, "en");
        } else {
            return mPrefs.getString(iKey, "");
        }
    }

    public String getToken(){
        return getString("accessToken");
    }

    public String getBearerToken(){
        return "Bearer "+getString("accessToken");
    }

    public String getRefresh(){
        return getString("refreshToken");
    }

    public void  saveGAId(String gaId){
        mPrefs.edit().putString("ga-property-id",gaId).apply();;
    }
    public String  getGAId(){
        return mPrefs.getString("ga-property-id",null);
    }
    public void removeItem(String iKey) {
        mPrefs.edit().remove(iKey).apply();
    }


    public void clearSavedData() {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.clear().apply();
    }

    public void saveVersionCode(int versionCode){
        mPrefs.edit().putInt("versionCode",versionCode).apply();
    }

    public void saveRateAppResponse(String response){
        mPrefs.edit().putString("rateApp",response).apply();
    }
    public String getRateAppResponse(){
        return mPrefs.getString("rateApp",null);
    }

    public void saveRemoveLogoAdPriority(String response){
        mPrefs.edit().putString("removeLogoadPriority",response).apply();
    }
    public String getRemoveLogoAdPriority(){
        return mPrefs.getString("removeLogoadPriority",null);
    }


    public int getVersionCode(){
        return mPrefs.getInt("versionCode",-1);
    }

    public ArrayList<String> getAIImagesStringList(String key) {
        return new ArrayList<>(Arrays.asList(TextUtils.split(mPrefs.getString(key, ""), "‚‗‚")));
    }

    /**
     * Put ArrayList of String into SharedPreferences with 'key' and save
     * @param key SharedPreferences key
     * @param tag String tag to place in list
     */
    public void putAIImagesSPList(String key, String tag) {
        //check if the tag is available
        // if available then dont edit and add to list
        // if not then create a list of +1 the existing list and add to the last item
        Boolean isItemAlreadyAdded = false;
        ArrayList<String> existingList = getAIImagesStringList(key);
        if (existingList.size() == 0){
            Log.e("asdfasdf", "inside putAIImagesSPList inside if tag: " + tag);
            String[] myStringList = new String[1];
            myStringList[0] = tag;
            mPrefs.edit().putString(key, TextUtils.join("‚‗‚", myStringList)).apply();
        } else {
            Log.e("asdfasdf", "inside putAIImagesSPList inside else tag: " + tag);
            for (int i =0; i < existingList.size();i++){
                if (existingList.get(i).equals(tag))
                    isItemAlreadyAdded = true;
            }
            if (!isItemAlreadyAdded) {
                String[] myStringList = new String[existingList.size() + 1];
                for (int i = 0; i < existingList.size(); i++) {
                    if (!existingList.get(i).equals(tag))
                        myStringList[i] = existingList.get(i);
                }
                myStringList[existingList.size()] = tag;
                mPrefs.edit().putString(key, TextUtils.join("‚‗‚", myStringList)).apply();
            }
        }
    }
}
