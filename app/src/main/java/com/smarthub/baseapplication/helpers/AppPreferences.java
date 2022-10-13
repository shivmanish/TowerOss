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




    public void saveIsCreatorLogin(boolean isCreator){
        SharedPreferences.Editor editor=mPrefs.edit();
        editor.putBoolean("isCreatorLogin",isCreator).apply();
    }

    public void saveStudioProfilePic(String url){
        SharedPreferences.Editor editor=mPrefs.edit();
        editor.putString("studioProfilePicUrl",url).apply();
    }
    public void saveStudioId(String studioId){
        SharedPreferences.Editor editor=mPrefs.edit();
        editor.putString("saveStudioId",studioId).apply();
    }
    public void saveShowAppOpenAd(boolean showOrNot){
        SharedPreferences.Editor editor=mPrefs.edit();
        editor.putBoolean("showAppOPenAd",showOrNot).apply();
    }
    public void saveShowExitDialogAd(boolean showOrNot){
        SharedPreferences.Editor editor=mPrefs.edit();
        editor.putBoolean("showExitDialog",showOrNot).apply();
    }

    public void saveShowAdWhileDownloading(boolean showOrNot){
        SharedPreferences.Editor editor=mPrefs.edit();
        editor.putBoolean("show_ad_download_popup",showOrNot).apply();
    }

    public void saveShowAdInEditImageScreen(boolean showOrNot){
        SharedPreferences.Editor editor=mPrefs.edit();
        editor.putBoolean("show_ad_in_edit_image",showOrNot).apply();
    }

    public void saveShowAdInExitDialog(boolean showOrNot){
        SharedPreferences.Editor editor=mPrefs.edit();
        editor.putBoolean("EXIT_APP_DIALOGUE_ADSLOT",showOrNot).apply();
    }

    public void saveNativeAdPosInImageFeed(int pos){
        SharedPreferences.Editor editor=mPrefs.edit();
        editor.putInt("show_native_in_image_feed_pos",pos).apply();
    }

    public int nativeAdPosInImageFeed(){ return mPrefs.getInt("show_native_in_image_feed_pos",0); }

    public void saveShowBannerInImageFeed(boolean showOrNot){
        SharedPreferences.Editor editor=mPrefs.edit();
        editor.putBoolean("show_banner_in_image_feed",showOrNot).apply();
    }

    public void saveShowNativeInImageFeed(boolean showOrNot){
        SharedPreferences.Editor editor=mPrefs.edit();
        editor.putBoolean("show_native_in_image_feed",showOrNot).apply();
    }


    public void saveShowInstruction(boolean showOrNot){
        SharedPreferences.Editor editor=mPrefs.edit();
        editor.putBoolean("is_instruction_shown",showOrNot).apply();
    }
    public void saveShowInstructionEmoji(boolean showOrNot){
        SharedPreferences.Editor editor=mPrefs.edit();
        editor.putBoolean("is_instruction_shown_emoji",showOrNot).apply();
    }

    public void saveShowBannerOrNativeAdInEditImageScreen(String nativeOrBanner){
        SharedPreferences.Editor editor=mPrefs.edit();
        editor.putString("show_native_or_banner_in_edit_image",nativeOrBanner).apply();
    }
    public void saveShowRewardedAdInEditImageScreen(boolean nativeOrBanner){
        SharedPreferences.Editor editor=mPrefs.edit();
        editor.putBoolean("show_rewarded_ad_in_edit_image",nativeOrBanner).apply();
    }
    public void saveShowAdForEditImage(boolean ad){
        SharedPreferences.Editor editor=mPrefs.edit();
        editor.putBoolean("show_ad_for_edit_image",ad).apply();
    }

    public boolean IsCreatorLogin(){
        return mPrefs.getBoolean("isCreatorLogin",false);
    }
    public boolean showAppOpenAD(){
        return mPrefs.getBoolean("showAppOPenAd",false);
    }
    public boolean getSupportLanSongSdk(){ return mPrefs.getBoolean("supports_lang_song_sdk",false); }
    public boolean showAdWhileDownloading(){ return mPrefs.getBoolean("show_ad_download_popup",false); }
    public boolean showAdInEditImageScreen(){ return mPrefs.getBoolean("show_ad_in_edit_image",false); }
    public boolean showAdInExitDialog(){ return mPrefs.getBoolean("EXIT_APP_DIALOGUE_ADSLOT",false); }
    public String showNativeOrBannerAdInEditImageScreen(){ return mPrefs.getString("show_native_or_banner_in_edit_image",null); }
    public boolean showRewardedAdInEditImageScreen(){ return mPrefs.getBoolean("show_rewarded_ad_in_edit_image",false); }
    public boolean showInstruction(){ return mPrefs.getBoolean("is_instruction_shown",true); }
    public boolean showInstructionEmoji(){ return mPrefs.getBoolean("is_instruction_shown_emoji",true); }
    public boolean showAdForEditImage(){ return mPrefs.getBoolean("show_ad_for_edit_image",true); }
    public boolean showNativeInImageFeed(){ return mPrefs.getBoolean("show_native_in_image_feed",false); }
    public boolean showBannerInImageFeed(){ return mPrefs.getBoolean("show_banner_in_image_feed",false); }
    public String getSelectedLanguageName(){return mPrefs.getString("languageName",null);}
    public String getSelectedLanguageId(){return mPrefs.getString("language",null);}
    public String getStudioProfilePicUrl(){ return mPrefs.getString("studioProfilePicUrl",null);
    }

    public String getStudioId(){
        return mPrefs.getString("saveStudioId",null);
    }

    public void saveSupportsLanSongSdk(boolean b) {
        SharedPreferences.Editor editor=mPrefs.edit();
        editor.putBoolean("supports_lang_song_sdk",b).apply();
    }
}
