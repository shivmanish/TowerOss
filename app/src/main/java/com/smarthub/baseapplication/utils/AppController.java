package com.smarthub.baseapplication.utils;

import android.app.Application;
import android.net.Uri;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.Observer;

import com.smarthub.baseapplication.helpers.Resource;
import com.smarthub.baseapplication.model.otp.GetOtpResponse;

import java.util.ArrayList;

public class AppController extends Application {

    static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Observer ob = new Observer<Resource<GetOtpResponse>>() {
            @Override
            public void onChanged(Resource<GetOtpResponse> getOtpResponseResource) {

            }
        };
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
