package com.smarthub.baseapplication.network.pojo.site_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RfAntenaModel implements Serializable {


    @SerializedName("Technology")
    @Expose
    private SiteInfoStatusData technology;


    @SerializedName("SpaceUsed")
    @Expose
    private SiteInfoStatusData spaceUsed;
    @SerializedName("TowerPoleId")
    @Expose
    private SiteInfoStatusData towerPoleId;
    @SerializedName("Sectornumber")
    @Expose
    private SiteInfoStatusData sectornumber;
    @SerializedName("AntenaShape")
    @Expose
    private SiteInfoStatusData antenaShape;
    @SerializedName("OwnerCompany")
    @Expose
    private SiteInfoStatusData ownerCompany;

    @SerializedName("UserCompany")
    @Expose
    private SiteInfoStatusData userCompany;
    @SerializedName("OperationalStatus")
    @Expose
    private SiteInfoStatusData operationalStatus;

    public SiteInfoStatusData getTechnology() {
        return technology;
    }

    public void setTechnology(SiteInfoStatusData technology) {
        this.technology = technology;
    }

    public SiteInfoStatusData getSpaceUsed() {
        return spaceUsed;
    }

    public void setSpaceUsed(SiteInfoStatusData spaceUsed) {
        this.spaceUsed = spaceUsed;
    }

    public SiteInfoStatusData getTowerPoleId() {
        return towerPoleId;
    }

    public void setTowerPoleId(SiteInfoStatusData towerPoleId) {
        this.towerPoleId = towerPoleId;
    }

    public SiteInfoStatusData getSectornumber() {
        return sectornumber;
    }

    public void setSectornumber(SiteInfoStatusData sectornumber) {
        this.sectornumber = sectornumber;
    }

    public SiteInfoStatusData getAntenaShape() {
        return antenaShape;
    }

    public void setAntenaShape(SiteInfoStatusData antenaShape) {
        this.antenaShape = antenaShape;
    }

    public SiteInfoStatusData getOwnerCompany() {
        return ownerCompany;
    }

    public void setOwnerCompany(SiteInfoStatusData ownerCompany) {
        this.ownerCompany = ownerCompany;
    }

    public SiteInfoStatusData getUserCompany() {
        return userCompany;
    }

    public void setUserCompany(SiteInfoStatusData userCompany) {
        this.userCompany = userCompany;
    }

    public SiteInfoStatusData getOperationalStatus() {
        return operationalStatus;
    }

    public void setOperationalStatus(SiteInfoStatusData operationalStatus) {
        this.operationalStatus = operationalStatus;
    }



}
