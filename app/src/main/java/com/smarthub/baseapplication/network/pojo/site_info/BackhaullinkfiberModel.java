package com.smarthub.baseapplication.network.pojo.site_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BackhaullinkfiberModel implements Serializable {
    @SerializedName("BackhaullinkType")
    @Expose
    private SiteInfoStatusData backhaullinkType;

    @SerializedName("BackhaulSiteCategory")
    @Expose
    private SiteInfoStatusData backhaulSiteCategory;
    @SerializedName("BackhaulLinkBandwidth")
    @Expose
    private SiteInfoStatusData backhaulLinkBandwidth;

    @SerializedName("ConnectedEQuipmentCEType")
    @Expose
    private SiteInfoStatusData connectedEQuipmentCEType;


    @SerializedName("BackhaulCeOperationalStatus")
    @Expose
    private SiteInfoStatusData backhaulCeOperationalStatus;
    @SerializedName("BackhaulIduInstalledLocationType")
    @Expose
    private SiteInfoStatusData backhaulIduInstalledLocationType;
    @SerializedName("BackhaulRackSpaceUsed")
    @Expose
    private SiteInfoStatusData backhaulRackSpaceUsed;
    @SerializedName("BackhaulCeInstallationVendor")
    @Expose
    private SiteInfoStatusData backhaulCeInstallationVendor;
    @SerializedName("BackhaulBuildFiberCore")
    @Expose
    private SiteInfoStatusData backhaulBuildFiberCore;
    @SerializedName("BackhaulInstallationVendor")
    @Expose
    private SiteInfoStatusData backhaulInstallationVendor;

    @SerializedName("BackhaulAcceptanceStatus")
    @Expose
    private SiteInfoStatusData backhaulAcceptanceStatus;
    @SerializedName("OwnerCompany")
    @Expose
    private SiteInfoStatusData ownerCompany;
    @SerializedName("OemCompany")
    @Expose
    private SiteInfoStatusData OemCompany;
    @SerializedName("BackhaulLinkUserCompany")
    @Expose
    private SiteInfoStatusData backhaulLinkUserCompany;
    @SerializedName("BackhaulLinkOperationalStatus")
    @Expose
    private SiteInfoStatusData backhaulLinkOperationalStatus;

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

    public SiteInfoStatusData getConnectedEQuipmentCEType() {
        return connectedEQuipmentCEType;
    }

    public void setConnectedEQuipmentCEType(SiteInfoStatusData connectedEQuipmentCEType) {
        this.connectedEQuipmentCEType = connectedEQuipmentCEType;
    }

    public SiteInfoStatusData getBackhaulCeOperationalStatus() {
        return backhaulCeOperationalStatus;
    }

    public void setBackhaulCeOperationalStatus(SiteInfoStatusData backhaulCeOperationalStatus) {
        this.backhaulCeOperationalStatus = backhaulCeOperationalStatus;
    }

    public SiteInfoStatusData getBackhaulIduInstalledLocationType() {
        return backhaulIduInstalledLocationType;
    }

    public void setBackhaulIduInstalledLocationType(SiteInfoStatusData backhaulIduInstalledLocationType) {
        this.backhaulIduInstalledLocationType = backhaulIduInstalledLocationType;
    }

    public SiteInfoStatusData getBackhaulRackSpaceUsed() {
        return backhaulRackSpaceUsed;
    }

    public void setBackhaulRackSpaceUsed(SiteInfoStatusData backhaulRackSpaceUsed) {
        this.backhaulRackSpaceUsed = backhaulRackSpaceUsed;
    }

    public SiteInfoStatusData getBackhaulCeInstallationVendor() {
        return backhaulCeInstallationVendor;
    }

    public void setBackhaulCeInstallationVendor(SiteInfoStatusData backhaulCeInstallationVendor) {
        this.backhaulCeInstallationVendor = backhaulCeInstallationVendor;
    }

    public SiteInfoStatusData getBackhaulBuildFiberCore() {
        return backhaulBuildFiberCore;
    }

    public void setBackhaulBuildFiberCore(SiteInfoStatusData backhaulBuildFiberCore) {
        this.backhaulBuildFiberCore = backhaulBuildFiberCore;
    }

    public SiteInfoStatusData getBackhaulInstallationVendor() {
        return backhaulInstallationVendor;
    }

    public void setBackhaulInstallationVendor(SiteInfoStatusData backhaulInstallationVendor) {
        this.backhaulInstallationVendor = backhaulInstallationVendor;
    }

    public SiteInfoStatusData getBackhaulAcceptanceStatus() {
        return backhaulAcceptanceStatus;
    }

    public void setBackhaulAcceptanceStatus(SiteInfoStatusData backhaulAcceptanceStatus) {
        this.backhaulAcceptanceStatus = backhaulAcceptanceStatus;
    }

    public SiteInfoStatusData getOwnerCompany() {
        return ownerCompany;
    }

    public void setOwnerCompany(SiteInfoStatusData ownerCompany) {
        this.ownerCompany = ownerCompany;
    }

    public SiteInfoStatusData getOemCompany() {
        return OemCompany;
    }

    public void setOemCompany(SiteInfoStatusData oemCompany) {
        OemCompany = oemCompany;
    }

    public SiteInfoStatusData getBackhaulLinkUserCompany() {
        return backhaulLinkUserCompany;
    }

    public void setBackhaulLinkUserCompany(SiteInfoStatusData backhaulLinkUserCompany) {
        this.backhaulLinkUserCompany = backhaulLinkUserCompany;
    }

    public SiteInfoStatusData getBackhaulLinkOperationalStatus() {
        return backhaulLinkOperationalStatus;
    }

    public void setBackhaulLinkOperationalStatus(SiteInfoStatusData backhaulLinkOperationalStatus) {
        this.backhaulLinkOperationalStatus = backhaulLinkOperationalStatus;
    }
}
