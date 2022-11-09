package com.smarthub.baseapplication.network.pojo.site_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OperationalInfoModel implements Serializable {

    @SerializedName("Sitebillingstatus")
    @Expose
    private SiteInfoStatusData sitebillingstatus;

    @SerializedName("Costcentre")
    @Expose
    private SiteInfoStatusData costcentre;

    @SerializedName("Sharingfeasibility")
    @Expose
    private SiteInfoStatusData sharingfeasibility;

    @SerializedName("Towncategory")
    @Expose
    private SiteInfoStatusData towncategor;

    @SerializedName("Hubsite")
    @Expose
    private SiteInfoStatusData hubsite;


    @SerializedName("Ldca")
    @Expose
    private SiteInfoStatusData ldca;


    @SerializedName("Scda")
    @Expose
    private SiteInfoStatusData scda;

    public SiteInfoStatusData getSitebillingstatus() {
        return sitebillingstatus;
    }

    public void setSitebillingstatus(SiteInfoStatusData sitebillingstatus) {
        this.sitebillingstatus = sitebillingstatus;
    }

    public SiteInfoStatusData getCostcentre() {
        return costcentre;
    }

    public void setCostcentre(SiteInfoStatusData costcentre) {
        this.costcentre = costcentre;
    }

    public SiteInfoStatusData getSharingfeasibility() {
        return sharingfeasibility;
    }

    public void setSharingfeasibility(SiteInfoStatusData sharingfeasibility) {
        this.sharingfeasibility = sharingfeasibility;
    }

    public SiteInfoStatusData getTowncategor() {
        return towncategor;
    }

    public void setTowncategor(SiteInfoStatusData towncategor) {
        this.towncategor = towncategor;
    }

    public SiteInfoStatusData getHubsite() {
        return hubsite;
    }

    public void setHubsite(SiteInfoStatusData hubsite) {
        this.hubsite = hubsite;
    }

    public SiteInfoStatusData getLdca() {
        return ldca;
    }

    public void setLdca(SiteInfoStatusData ldca) {
        this.ldca = ldca;
    }

    public SiteInfoStatusData getScda() {
        return scda;
    }

    public void setScda(SiteInfoStatusData scda) {
        this.scda = scda;
    }
}
