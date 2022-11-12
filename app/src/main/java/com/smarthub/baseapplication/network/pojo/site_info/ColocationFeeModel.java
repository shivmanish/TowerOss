package com.smarthub.baseapplication.network.pojo.site_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ColocationFeeModel implements Serializable {
    @SerializedName("Paymentfreq")
    @Expose
    private SiteInfoStatusData paymentfreq;
    public SiteInfoStatusData getPaymentfreq() {
        return paymentfreq;
    }

    public void setPaymentfreq(SiteInfoStatusData paymentfreq) {
        this.paymentfreq = paymentfreq;
    }




}
