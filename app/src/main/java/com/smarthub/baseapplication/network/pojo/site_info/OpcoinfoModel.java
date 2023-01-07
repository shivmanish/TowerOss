package com.smarthub.baseapplication.network.pojo.site_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OpcoinfoModel implements Serializable {
    @SerializedName("Opcositestatus")
    @Expose
    private SiteInfoStatusData opcositestatus;

    @SerializedName("Opcositetype")
    @Expose
    private SiteInfoStatusData opcositetype;


    @SerializedName("Operatornetworktype")
    @Expose
    private SiteInfoStatusData operatornetworktype;

    @SerializedName("Alarmsextension")
    @Expose
    private SiteInfoStatusData alarmsextension;

    @SerializedName("Rftechnology")
    @Expose
    private SiteInfoStatusData rftechnology;
    @SerializedName("Telecomequipmenttype")
    @Expose
    private SiteInfoStatusData telecomequipmenttype;

    @SerializedName("Rrucount")
    @Expose
    private SiteInfoStatusData rrucount;

    @SerializedName("Sectorcount")
    @Expose
    private SiteInfoStatusData sectorcount;

    @SerializedName("Rackcount")
    @Expose
    private SiteInfoStatusData rackcount;

    @SerializedName("Antenacount")
    @Expose
    private SiteInfoStatusData antenacount;

    @SerializedName("Antenaslotused")
    @Expose
    private SiteInfoStatusData antenaslotused;

    @SerializedName("InstallationVendor")
    @Expose
    private SiteInfoStatusData installationVendor;
    @SerializedName("MaintenanceVendor")
    @Expose
    private SiteInfoStatusData maintenanceVendor;

    @SerializedName("Backhaultechnology")
    @Expose
    private SiteInfoStatusData backhaultechnology;

    @SerializedName("OpcoName")
    @Expose
    private SiteInfoStatusData opcoName;

    public SiteInfoStatusData getOpcositestatus() {
        return opcositestatus;
    }

    public void setOpcositestatus(SiteInfoStatusData opcositestatus) {
        this.opcositestatus = opcositestatus;
    }

    public SiteInfoStatusData getOpcositetype() {
        return opcositetype;
    }

    public void setOpcositetype(SiteInfoStatusData opcositetype) {
        this.opcositetype = opcositetype;
    }

    public SiteInfoStatusData getOperatornetworktype() {
        return operatornetworktype;
    }

    public void setOperatornetworktype(SiteInfoStatusData operatornetworktype) {
        this.operatornetworktype = operatornetworktype;
    }

    public SiteInfoStatusData getAlarmsextension() {
        return alarmsextension;
    }

    public void setAlarmsextension(SiteInfoStatusData alarmsextension) {
        this.alarmsextension = alarmsextension;
    }

    public SiteInfoStatusData getRftechnology() {
        return rftechnology;
    }

    public void setRftechnology(SiteInfoStatusData rftechnology) {
        this.rftechnology = rftechnology;
    }

    public SiteInfoStatusData getTelecomequipmenttype() {
        return telecomequipmenttype;
    }

    public void setTelecomequipmenttype(SiteInfoStatusData telecomequipmenttype) {
        this.telecomequipmenttype = telecomequipmenttype;
    }

    public SiteInfoStatusData getRrucount() {
        return rrucount;
    }

    public void setRrucount(SiteInfoStatusData rrucount) {
        this.rrucount = rrucount;
    }

    public SiteInfoStatusData getSectorcount() {
        return sectorcount;
    }

    public void setSectorcount(SiteInfoStatusData sectorcount) {
        this.sectorcount = sectorcount;
    }

    public SiteInfoStatusData getRackcount() {
        return rackcount;
    }

    public void setRackcount(SiteInfoStatusData rackcount) {
        this.rackcount = rackcount;
    }

    public SiteInfoStatusData getAntenacount() {
        return antenacount;
    }

    public void setAntenacount(SiteInfoStatusData antenacount) {
        this.antenacount = antenacount;
    }

    public SiteInfoStatusData getAntenaslotused() {
        return antenaslotused;
    }

    public void setAntenaslotused(SiteInfoStatusData atenaslotused) {
        this.antenaslotused = atenaslotused;
    }

    public SiteInfoStatusData getInstallationVendor() {
        return installationVendor;
    }

    public void setInstallationVendor(SiteInfoStatusData installationVendor) {
        this.installationVendor = installationVendor;
    }

    public SiteInfoStatusData getMaintenanceVendor() {
        return maintenanceVendor;
    }

    public void setMaintenanceVendor(SiteInfoStatusData maintenanceVendor) {
        this.maintenanceVendor = maintenanceVendor;
    }

    public SiteInfoStatusData getBackhaultechnology() {
        return backhaultechnology;
    }

    public void setBackhaultechnology(SiteInfoStatusData backhaultechnology) {
        this.backhaultechnology = backhaultechnology;
    }

    public SiteInfoStatusData getOpcoName() {
        return opcoName;
    }

    public void setOpcoName(SiteInfoStatusData opcoName) {
        this.opcoName = opcoName;
    }


}
