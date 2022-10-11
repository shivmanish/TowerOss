package com.smarthub.baseapplication.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileDetails {
    @SerializedName("first_name")
    @Expose
    private String first_name;

    public String getData() {
        return first_name;
    }

    public void setData(String data) {
        this.first_name = data;
    }

}
