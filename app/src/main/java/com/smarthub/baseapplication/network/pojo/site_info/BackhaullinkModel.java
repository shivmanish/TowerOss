package com.smarthub.baseapplication.network.pojo.site_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BackhaullinkModel implements Serializable {


    @SerializedName("BackhaulType")
    @Expose
    private SiteInfoStatusData backhaulType;

    @SerializedName("BackhaullinkType")
    @Expose
    private SiteInfoStatusData backhaullinkType;


    @SerializedName("BackhaulSiteCategory")
    @Expose
    private SiteInfoStatusData backhaulSiteCategory;

    @SerializedName("BackhaulLinkBandwidth")
    @Expose
    private SiteInfoStatusData backhaulLinkBandwidth;
    @SerializedName("Backhaulmwfrequency")
    @Expose
    private SiteInfoStatusData backhaulmwfrequency;
    @SerializedName("BackhaulIduInstalledLocationType")
    @Expose
    private SiteInfoStatusData backhaulIduInstalledLocationType;
    @SerializedName("BackhaulRackID")
    @Expose
    private SiteInfoStatusData BackhaulRackID;
    @SerializedName("BackhaulRackSpaceUsed")
    @Expose
    private SiteInfoStatusData backhaulRackSpaceUsed;
    @SerializedName("BackhaulSpaceUsed")
    @Expose
    private SiteInfoStatusData backhaulSpaceUsed;
    @SerializedName("BackhaulTowerPoleId")
    @Expose
    private SiteInfoStatusData backhaulTowerPoleId;
    @SerializedName("BackhaulAntenaShape")
    @Expose
    private SiteInfoStatusData backhaulAntenaShape;
    @SerializedName("BackhaulAntenaSpaceUsed")
    @Expose
    private SiteInfoStatusData backhaulAntenaSpaceUsed;
    @SerializedName("BackhaulAntenaTowerPoleId")
    @Expose
    private SiteInfoStatusData backhaulAntenaTowerPoleId;
    @SerializedName("OwnerCompany")
    @Expose
    private SiteInfoStatusData ownerCompany;
    @SerializedName("OemCompany")
    @Expose
    private SiteInfoStatusData oemCompany;
    @SerializedName("OperationStatus")
    @Expose
    private SiteInfoStatusData operationStatus;

    @SerializedName("BackhaulInstallationVendor")
    @Expose
    private SiteInfoStatusData backhaulInstallationVendor;
    public SiteInfoStatusData getBackhaulType() {
        return backhaulType;
    }

    public void setBackhaulType(SiteInfoStatusData backhaulType) {
        this.backhaulType = backhaulType;
    }

    public SiteInfoStatusData getBackhaullinkType() {
        return backhaullinkType;
    }

    public void setBackhaullinkType(SiteInfoStatusData backhaullinkType) {
        this.backhaullinkType = backhaullinkType;
    }

    public SiteInfoStatusData getBackhaulSiteCategory() {
        return backhaulSiteCategory;
    }

    public void setBackhaulSiteCategory(SiteInfoStatusData backhaulSiteCategory) {
        this.backhaulSiteCategory = backhaulSiteCategory;
    }

    public SiteInfoStatusData getBackhaulLinkBandwidth() {
        return backhaulLinkBandwidth;
    }

    public void setBackhaulLinkBandwidth(SiteInfoStatusData backhaulLinkBandwidth) {
        this.backhaulLinkBandwidth = backhaulLinkBandwidth;
    }

    public SiteInfoStatusData getBackhaulmwfrequency() {
        return backhaulmwfrequency;
    }

    public void setBackhaulmwfrequency(SiteInfoStatusData backhaulmwfrequency) {
        this.backhaulmwfrequency = backhaulmwfrequency;
    }

    public SiteInfoStatusData getBackhaulIduInstalledLocationType() {
        return backhaulIduInstalledLocationType;
    }

    public void setBackhaulIduInstalledLocationType(SiteInfoStatusData backhaulIduInstalledLocationType) {
        this.backhaulIduInstalledLocationType = backhaulIduInstalledLocationType;
    }

    public SiteInfoStatusData getBackhaulRackID() {
        return BackhaulRackID;
    }

    public void setBackhaulRackID(SiteInfoStatusData backhaulRackID) {
        BackhaulRackID = backhaulRackID;
    }

    public SiteInfoStatusData getBackhaulRackSpaceUsed() {
        return backhaulRackSpaceUsed;
    }

    public void setBackhaulRackSpaceUsed(SiteInfoStatusData backhaulRackSpaceUsed) {
        this.backhaulRackSpaceUsed = backhaulRackSpaceUsed;
    }

    public SiteInfoStatusData getBackhaulSpaceUsed() {
        return backhaulSpaceUsed;
    }

    public void setBackhaulSpaceUsed(SiteInfoStatusData backhaulSpaceUsed) {
        this.backhaulSpaceUsed = backhaulSpaceUsed;
    }

    public SiteInfoStatusData getBackhaulTowerPoleId() {
        return backhaulTowerPoleId;
    }

    public void setBackhaulTowerPoleId(SiteInfoStatusData backhaulTowerPoleId) {
        this.backhaulTowerPoleId = backhaulTowerPoleId;
    }

    public SiteInfoStatusData getBackhaulAntenaShape() {
        return backhaulAntenaShape;
    }

    public void setBackhaulAntenaShape(SiteInfoStatusData backhaulAntenaShape) {
        this.backhaulAntenaShape = backhaulAntenaShape;
    }

    public SiteInfoStatusData getBackhaulAntenaSpaceUsed() {
        return backhaulAntenaSpaceUsed;
    }

    public void setBackhaulAntenaSpaceUsed(SiteInfoStatusData backhaulAntenaSpaceUsed) {
        this.backhaulAntenaSpaceUsed = backhaulAntenaSpaceUsed;
    }

    public SiteInfoStatusData getBackhaulAntenaTowerPoleId() {
        return backhaulAntenaTowerPoleId;
    }

    public void setBackhaulAntenaTowerPoleId(SiteInfoStatusData backhaulAntenaTowerPoleId) {
        this.backhaulAntenaTowerPoleId = backhaulAntenaTowerPoleId;
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

    public SiteInfoStatusData getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(SiteInfoStatusData operationStatus) {
        this.operationStatus = operationStatus;
    }

    public SiteInfoStatusData getBackhaulInstallationVendor() {
        return backhaulInstallationVendor;
    }

    public void setBackhaulInstallationVendor(SiteInfoStatusData backhaulInstallationVendor) {
        this.backhaulInstallationVendor = backhaulInstallationVendor;
    }



}
