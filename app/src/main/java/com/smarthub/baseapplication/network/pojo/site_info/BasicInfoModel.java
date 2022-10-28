package com.smarthub.baseapplication.network.pojo.site_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BasicInfoModel {

    @SerializedName("Sitestatus")
    @Expose
    private SiteInfoStatusData sitestatus;

    @SerializedName("Sitecategory")
    @Expose
    private SiteInfoStatusData sitecategory;

    @SerializedName("Sitetype")
    @Expose
    private SiteInfoStatusData sitetype;

    @SerializedName("Siteownership")
    @Expose
    private SiteInfoStatusData siteownership;

    @SerializedName("Buildingtype")
    @Expose
    private SiteInfoStatusData buildingtype;


    public SiteInfoStatusData getSitestatus() {
        return sitestatus;
    }

    public void setSitestatus(SiteInfoStatusData sitestatus) {
        this.sitestatus = sitestatus;
    }

    public SiteInfoStatusData getSitecategory() {
        return sitecategory;
    }

    public void setSitecategory(SiteInfoStatusData sitecategory) {
        this.sitecategory = sitecategory;
    }

    public SiteInfoStatusData getSitetype() {
        return sitetype;
    }

    public void setSitetype(SiteInfoStatusData sitetype) {
        this.sitetype = sitetype;
    }

    public SiteInfoStatusData getSiteownership() {
        return siteownership;
    }

    public void setSiteownership(SiteInfoStatusData siteownership) {
        this.siteownership = siteownership;
    }

    public SiteInfoStatusData getBuildingtype() {
        return buildingtype;
    }

    public void setBuildingtype(SiteInfoStatusData buildingtype) {
        this.buildingtype = buildingtype;
    }
}
