package com.smarthub.baseapplication.network.pojo.site_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PowerLoadModel implements Serializable {
    @SerializedName("Loadtype")
    @Expose
    private SiteInfoStatusData loadtype;

    public SiteInfoStatusData getLoadtype() {
        return loadtype;
    }

    public void setLoadtype(SiteInfoStatusData loadtype) {
        this.loadtype = loadtype;
    }




}
