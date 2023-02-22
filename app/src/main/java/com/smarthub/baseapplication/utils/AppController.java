package com.smarthub.baseapplication.utils;

import android.app.Application;
import android.net.Uri;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.Observer;

import com.example.trackermodule.util.MyApplication;
import com.smarthub.baseapplication.helpers.Resource;
import com.smarthub.baseapplication.model.otp.GetOtpResponse;
import com.smarthub.baseapplication.model.siteInfo.SiteInfoModel;
import com.smarthub.baseapplication.model.siteInfo.siteInfoData.SiteInfoDataModel;

import java.util.ArrayList;

public class AppController extends MyApplication {

    static AppController mInstance;
    public SiteInfoDataModel siteInfoModel;
    public String ownerName = "SMRT";
    public String siteid = "448";

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }
    ArrayList<Uri> pickedImages = new ArrayList();
    public ArrayList<Uri> getPickedImages() {
        return pickedImages;
    }

    public void setPickedImages(ArrayList<Uri> pickedImages) {
        this.pickedImages = pickedImages;
    }

}
