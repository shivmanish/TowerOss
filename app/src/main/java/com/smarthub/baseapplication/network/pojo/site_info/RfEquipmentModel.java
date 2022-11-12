package com.smarthub.baseapplication.network.pojo.site_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RfEquipmentModel implements Serializable {


    @SerializedName("Technology")
    @Expose
    private SiteInfoStatusData technology;

    @SerializedName("OwnerCompany")
    @Expose
    private SiteInfoStatusData ownerCompany;
    @SerializedName("OemCompany")
    @Expose
    private SiteInfoStatusData oemCompany;
    @SerializedName("Usagetype")
    @Expose
    private SiteInfoStatusData usagetype;
    @SerializedName("OperationStatus")
    @Expose
    private SiteInfoStatusData operationStatus;

    public SiteInfoStatusData getTechnology() {
        return technology;
    }

    public void setTechnology(SiteInfoStatusData technology) {
        this.technology = technology;
    }

    public SiteInfoStatusData getOwnerCompany() {
        return ownerCompany;
    }

    public void setOwnerCompany(SiteInfoStatusData ownerCompany) {
        this.ownerCompany = ownerCompany;
    }

    public SiteInfoStatusData getOemCompany() {
        return oemCompany;
    }

    public void setOemCompany(SiteInfoStatusData oemCompany) {
        this.oemCompany = oemCompany;
    }

    public SiteInfoStatusData getUsagetype() {
        return usagetype;
    }

    public void setUsagetype(SiteInfoStatusData usagetype) {
        this.usagetype = usagetype;
    }

    public SiteInfoStatusData getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(SiteInfoStatusData operationStatus) {
        this.operationStatus = operationStatus;
    }

    public SiteInfoStatusData getRackSpaceUsed() {
        return rackSpaceUsed;
    }

    public void setRackSpaceUsed(SiteInfoStatusData rackSpaceUsed) {
        this.rackSpaceUsed = rackSpaceUsed;
    }

    @SerializedName("RackSpaceUsed")
    @Expose
    private SiteInfoStatusData rackSpaceUsed;



}
