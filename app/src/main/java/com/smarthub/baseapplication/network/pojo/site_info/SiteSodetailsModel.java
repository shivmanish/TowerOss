package com.smarthub.baseapplication.network.pojo.site_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SiteSodetailsModel implements Serializable {
    @SerializedName("Lockingperiod")
    @Expose
    private SiteInfoStatusData lockingperiod;

    @SerializedName("Feeesclationsperiod")
    @Expose
    private SiteInfoStatusData feeesclationsperiod;
    @SerializedName("Escalationpercentage")
    @Expose
    private SiteInfoStatusData escalationpercentage;
    @SerializedName("Premiumapplicable")
    @Expose
    private SiteInfoStatusData premiumapplicable;

    @SerializedName("Discounttype")
    @Expose
    private SiteInfoStatusData discounttype;
    @SerializedName("Discountpercentage")
    @Expose
    private SiteInfoStatusData discountpercentage;

    public SiteInfoStatusData getLockingperiod() {
        return lockingperiod;
    }

    public void setLockingperiod(SiteInfoStatusData lockingperiod) {
        this.lockingperiod = lockingperiod;
    }

    public SiteInfoStatusData getFeeesclationsperiod() {
        return feeesclationsperiod;
    }

    public void setFeeesclationsperiod(SiteInfoStatusData feeesclationsperiod) {
        this.feeesclationsperiod = feeesclationsperiod;
    }

    public SiteInfoStatusData getEscalationpercentage() {
        return escalationpercentage;
    }

    public void setEscalationpercentage(SiteInfoStatusData escalationpercentage) {
        this.escalationpercentage = escalationpercentage;
    }

    public SiteInfoStatusData getPremiumapplicable() {
        return premiumapplicable;
    }

    public void setPremiumapplicable(SiteInfoStatusData premiumapplicable) {
        this.premiumapplicable = premiumapplicable;
    }

    public SiteInfoStatusData getDiscounttype() {
        return discounttype;
    }

    public void setDiscounttype(SiteInfoStatusData discounttype) {
        this.discounttype = discounttype;
    }

    public SiteInfoStatusData getDiscountpercentage() {
        return discountpercentage;
    }

    public void setDiscountpercentage(SiteInfoStatusData discountpercentage) {
        this.discountpercentage = discountpercentage;
    }

    public SiteInfoStatusData getMonthlypremiumamount() {
        return monthlypremiumamount;
    }

    public void setMonthlypremiumamount(SiteInfoStatusData monthlypremiumamount) {
        this.monthlypremiumamount = monthlypremiumamount;
    }

    @SerializedName("Monthlypremiumamount")
    @Expose
    private SiteInfoStatusData monthlypremiumamount;


}
