package com.smarthub.baseapplication.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RefreshToken {
    @SerializedName("refresh")
    @Expose
    private String refresh;

    @SerializedName("access")
    @Expose
    private String access;

    public String getAccess() {
        return access;
    }

    public String getRefresh() {
        return refresh;
    }

    public void setAccess(String status) {
        this.access = status;
    }

    public void setRefresh(String refresh) {
        this.access = refresh;
    }

    public String getData() {
        return refresh;
    }
}
