package com.smarthub.baseapplication.network.pojo.site_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BackhaullinkModel implements Serializable {

    @SerializedName("Backhaullinkmw")
    @Expose
    private BackhaullinkmwModel backhaullinkmw;

     @SerializedName("Backhaullinkfiber")
    @Expose
    private BackhaullinkfiberModel backhaullinkfiber;

}
