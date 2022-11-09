package com.smarthub.baseapplication.network.pojo.site_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SafetyAndAccessModel implements Serializable {

    @SerializedName("Physicalsecurity")
    @Expose
    private SiteInfoStatusData physicalsecurity;

    @SerializedName("GateAndFence")
    @Expose
    private SiteInfoStatusData gateAndFence;

    @SerializedName("Videomonitoring")
    @Expose
    private SiteInfoStatusData videomonitoring;

    @SerializedName("SiteAccessArea")
    @Expose
    private SiteInfoStatusData siteAccessArea;

    @SerializedName("DangerSignage")
    @Expose
    private SiteInfoStatusData dangerSignage;


    @SerializedName("CautionSignage")
    @Expose
    private SiteInfoStatusData cautionSignage;


    @SerializedName("Siteaccess")
    @Expose
    private SiteInfoStatusData siteaccess;

    public SiteInfoStatusData getPhysicalsecurity() {
        return physicalsecurity;
    }

    public void setPhysicalsecurity(SiteInfoStatusData physicalsecurity) {
        this.physicalsecurity = physicalsecurity;
    }

    public SiteInfoStatusData getGateAndFence() {
        return gateAndFence;
    }

    public void setGateAndFence(SiteInfoStatusData gateAndFence) {
        this.gateAndFence = gateAndFence;
    }

    public SiteInfoStatusData getVideomonitoring() {
        return videomonitoring;
    }

    public void setVideomonitoring(SiteInfoStatusData videomonitoring) {
        this.videomonitoring = videomonitoring;
    }

    public SiteInfoStatusData getSiteAccessArea() {
        return siteAccessArea;
    }

    public void setSiteAccessArea(SiteInfoStatusData siteAccessArea) {
        this.siteAccessArea = siteAccessArea;
    }

    public SiteInfoStatusData getDangerSignage() {
        return dangerSignage;
    }

    public void setDangerSignage(SiteInfoStatusData dangerSignage) {
        this.dangerSignage = dangerSignage;
    }

    public SiteInfoStatusData getCautionSignage() {
        return cautionSignage;
    }

    public void setCautionSignage(SiteInfoStatusData cautionSignage) {
        this.cautionSignage = cautionSignage;
    }

    public SiteInfoStatusData getSiteaccess() {
        return siteaccess;
    }

    public void setSiteaccess(SiteInfoStatusData siteaccess) {
        this.siteaccess = siteaccess;
    }
}
