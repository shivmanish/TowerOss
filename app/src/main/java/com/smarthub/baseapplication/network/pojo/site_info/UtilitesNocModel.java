package com.smarthub.baseapplication.network.pojo.site_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UtilitesNocModel implements Serializable {
    @SerializedName("Utilitiesequipmenttype")
    @Expose
    private SiteInfoStatusData utilitiesequipmenttype;
    @SerializedName("Utilitiesequipmentname")
    @Expose
    private SiteInfoStatusData utilitiesequipmentname;
    @SerializedName("Equipmentownertype")
    @Expose
    private SiteInfoStatusData equipmentownertype;
    @SerializedName("Usagetype")
    @Expose
    private SiteInfoStatusData usagetype;
    @SerializedName("SmpsRatingCapacity")
    @Expose
    private SiteInfoStatusData smpsRatingCapacity;
    @SerializedName("SmpsInstalledLocationType")
    @Expose
    private SiteInfoStatusData smpsInstalledLocationType;
    @SerializedName("SmpsRackId")
    @Expose
    private SiteInfoStatusData SmpsRackId;
    @SerializedName("SmpsOwnerCompany")
    @Expose
    private SiteInfoStatusData SmpsUserCompany;
    @SerializedName("SmpsOwnerCompany")
    @Expose
    private SiteInfoStatusData smpsUserCompany;
    @SerializedName("SmpsInstallationVendor")
    @Expose
    private SiteInfoStatusData smpsInstallationVendor;

    @SerializedName("SmpsIPVoltageReading")
    @Expose
    private SiteInfoStatusData smpsIPVoltageReading;
    @SerializedName("SmpsOPVolatgeReading")
    @Expose
    private SiteInfoStatusData smpsOPVolatgeReading;
    @SerializedName("SmpsAcceptanceStatus")
    @Expose
    private SiteInfoStatusData smpsAcceptanceStatus;
    @SerializedName("SmpsOperationStatus")
    @Expose
    private SiteInfoStatusData smpsOperationStatus;
    @SerializedName("SmpsMaintenanceVendor")
    @Expose
    private SiteInfoStatusData smpsMaintenanceVendor;
    @SerializedName("Batterybankequipmentname")
    @Expose
    private SiteInfoStatusData batterybankequipmentname;
    @SerializedName("BatterybankRatingCapacity")
    @Expose
    private SiteInfoStatusData batterybankRatingCapacity;
    @SerializedName("BatterybankInstalledLocationType")
    @Expose
    private SiteInfoStatusData batterybankInstalledLocationType;
    @SerializedName("BatterybankRackId")
    @Expose
    private SiteInfoStatusData BatterybankOwnerCompany;
    @SerializedName("BatterybankRackId")
    @Expose
    private SiteInfoStatusData batterybankRackId;
    @SerializedName("BatterybankUserCompany")
    @Expose
    private SiteInfoStatusData batterybankUserCompany;
    @SerializedName("BatterybankInstallationVendor")
    @Expose
    private SiteInfoStatusData batterybankInstallationVendor;
    @SerializedName("BatterybankIPVoltageReading")
    @Expose
    private SiteInfoStatusData batterybankIPVoltageReading;
    @SerializedName("BatterybankOPVolatgeReading")
    @Expose
    private SiteInfoStatusData batterybankOPVolatgeReading;
    @SerializedName("BatterybankAcceptanceStatus")
    @Expose
    private SiteInfoStatusData batterybankAcceptanceStatus;
    @SerializedName("BatterybankOperationStatus")
    @Expose
    private SiteInfoStatusData batterybankOperationStatus;
    @SerializedName("BatterybankMaintenanceVendor")
    @Expose
    private SiteInfoStatusData batterybankMaintenanceVendor;
    @SerializedName("Dgequipmentname")
    @Expose
    private SiteInfoStatusData dgequipmentname;
    @SerializedName("DgRatingCapacity")
    @Expose
    private SiteInfoStatusData dgRatingCapacity;
    @SerializedName("DgCanopyAvailable")
    @Expose
    private SiteInfoStatusData dgCanopyAvailable;
    @SerializedName("DgInstalledLocationType")
    @Expose
    private SiteInfoStatusData dgInstalledLocationType;
    @SerializedName("DgInstallationType")
    @Expose
    private SiteInfoStatusData dgInstallationType;
    @SerializedName("DgFuelType")
    @Expose
    private SiteInfoStatusData dgFuelType;
    @SerializedName("DgOwnerCompany")
    @Expose
    private SiteInfoStatusData dgOwnerCompany;
    @SerializedName("DgUserCompany")
    @Expose
    private SiteInfoStatusData DgUserCompany;
    @SerializedName("DgInstallationVendor")
    @Expose
    private SiteInfoStatusData dgInstallationVendor;
    @SerializedName("DgIPVoltageReading")
    @Expose
    private SiteInfoStatusData dgIPVoltageReading;
    @SerializedName("DgOPVolatgeReading")
    @Expose
    private SiteInfoStatusData dgOPVolatgeReading;
    @SerializedName("DgAcceptanceStatus")
    @Expose
    private SiteInfoStatusData dgAcceptanceStatus;
    @SerializedName("DgOperationStatus")
    @Expose
    private SiteInfoStatusData dgOperationStatus;
    @SerializedName("Acequipmentname")
    @Expose
    private SiteInfoStatusData Acequipmentname;
    @SerializedName("AcRatingCapacity")
    @Expose
    private SiteInfoStatusData acRatingCapacity;
    @SerializedName("SmpsName")
    @Expose
    private SiteInfoStatusData smpsName;
    @SerializedName("AcOwnerCompany")
    @Expose
    private SiteInfoStatusData acOwnerCompany;
    @SerializedName("AcOwnerCompany")
    @Expose
    private SiteInfoStatusData AcUserCompany;
    @SerializedName("AcInstallationVendor")
    @Expose
    private SiteInfoStatusData acInstallationVendor;
    @SerializedName("acAcceptanceStatus")
    @Expose
    private SiteInfoStatusData AcAcceptanceStatus;
    @SerializedName("AcOperationStatus")
    @Expose
    private SiteInfoStatusData acOperationStatus;

    public SiteInfoStatusData getUtilitiesequipmenttype() {
        return utilitiesequipmenttype;
    }

    public void setUtilitiesequipmenttype(SiteInfoStatusData utilitiesequipmenttype) {
        this.utilitiesequipmenttype = utilitiesequipmenttype;
    }

    public SiteInfoStatusData getUtilitiesequipmentname() {
        return utilitiesequipmentname;
    }

    public void setUtilitiesequipmentname(SiteInfoStatusData utilitiesequipmentname) {
        this.utilitiesequipmentname = utilitiesequipmentname;
    }

    public SiteInfoStatusData getEquipmentownertype() {
        return equipmentownertype;
    }

    public void setEquipmentownertype(SiteInfoStatusData equipmentownertype) {
        this.equipmentownertype = equipmentownertype;
    }

    public SiteInfoStatusData getUsagetype() {
        return usagetype;
    }

    public void setUsagetype(SiteInfoStatusData usagetype) {
        this.usagetype = usagetype;
    }

    public SiteInfoStatusData getSmpsRatingCapacity() {
        return smpsRatingCapacity;
    }

    public void setSmpsRatingCapacity(SiteInfoStatusData smpsRatingCapacity) {
        this.smpsRatingCapacity = smpsRatingCapacity;
    }

    public SiteInfoStatusData getSmpsInstalledLocationType() {
        return smpsInstalledLocationType;
    }

    public void setSmpsInstalledLocationType(SiteInfoStatusData smpsInstalledLocationType) {
        this.smpsInstalledLocationType = smpsInstalledLocationType;
    }

    public SiteInfoStatusData getSmpsRackId() {
        return SmpsRackId;
    }

    public void setSmpsRackId(SiteInfoStatusData smpsRackId) {
        SmpsRackId = smpsRackId;
    }

    public SiteInfoStatusData getSmpsUserCompany() {
        return SmpsUserCompany;
    }

    public void setSmpsUserCompany(SiteInfoStatusData smpsUserCompany) {
        SmpsUserCompany = smpsUserCompany;
    }

    public SiteInfoStatusData getSmpsInstallationVendor() {
        return smpsInstallationVendor;
    }

    public void setSmpsInstallationVendor(SiteInfoStatusData smpsInstallationVendor) {
        this.smpsInstallationVendor = smpsInstallationVendor;
    }

    public SiteInfoStatusData getSmpsIPVoltageReading() {
        return smpsIPVoltageReading;
    }

    public void setSmpsIPVoltageReading(SiteInfoStatusData smpsIPVoltageReading) {
        this.smpsIPVoltageReading = smpsIPVoltageReading;
    }

    public SiteInfoStatusData getSmpsOPVolatgeReading() {
        return smpsOPVolatgeReading;
    }

    public void setSmpsOPVolatgeReading(SiteInfoStatusData smpsOPVolatgeReading) {
        this.smpsOPVolatgeReading = smpsOPVolatgeReading;
    }

    public SiteInfoStatusData getSmpsAcceptanceStatus() {
        return smpsAcceptanceStatus;
    }

    public void setSmpsAcceptanceStatus(SiteInfoStatusData smpsAcceptanceStatus) {
        this.smpsAcceptanceStatus = smpsAcceptanceStatus;
    }

    public SiteInfoStatusData getSmpsOperationStatus() {
        return smpsOperationStatus;
    }

    public void setSmpsOperationStatus(SiteInfoStatusData smpsOperationStatus) {
        this.smpsOperationStatus = smpsOperationStatus;
    }

    public SiteInfoStatusData getSmpsMaintenanceVendor() {
        return smpsMaintenanceVendor;
    }

    public void setSmpsMaintenanceVendor(SiteInfoStatusData smpsMaintenanceVendor) {
        this.smpsMaintenanceVendor = smpsMaintenanceVendor;
    }

    public SiteInfoStatusData getBatterybankequipmentname() {
        return batterybankequipmentname;
    }

    public void setBatterybankequipmentname(SiteInfoStatusData batterybankequipmentname) {
        this.batterybankequipmentname = batterybankequipmentname;
    }

    public SiteInfoStatusData getBatterybankRatingCapacity() {
        return batterybankRatingCapacity;
    }

    public void setBatterybankRatingCapacity(SiteInfoStatusData batterybankRatingCapacity) {
        this.batterybankRatingCapacity = batterybankRatingCapacity;
    }

    public SiteInfoStatusData getBatterybankInstalledLocationType() {
        return batterybankInstalledLocationType;
    }

    public void setBatterybankInstalledLocationType(SiteInfoStatusData batterybankInstalledLocationType) {
        this.batterybankInstalledLocationType = batterybankInstalledLocationType;
    }

    public SiteInfoStatusData getBatterybankOwnerCompany() {
        return BatterybankOwnerCompany;
    }

    public void setBatterybankOwnerCompany(SiteInfoStatusData batterybankOwnerCompany) {
        BatterybankOwnerCompany = batterybankOwnerCompany;
    }

    public SiteInfoStatusData getBatterybankRackId() {
        return batterybankRackId;
    }

    public void setBatterybankRackId(SiteInfoStatusData batterybankRackId) {
        this.batterybankRackId = batterybankRackId;
    }

    public SiteInfoStatusData getBatterybankUserCompany() {
        return batterybankUserCompany;
    }

    public void setBatterybankUserCompany(SiteInfoStatusData batterybankUserCompany) {
        this.batterybankUserCompany = batterybankUserCompany;
    }

    public SiteInfoStatusData getBatterybankInstallationVendor() {
        return batterybankInstallationVendor;
    }

    public void setBatterybankInstallationVendor(SiteInfoStatusData batterybankInstallationVendor) {
        this.batterybankInstallationVendor = batterybankInstallationVendor;
    }

    public SiteInfoStatusData getBatterybankIPVoltageReading() {
        return batterybankIPVoltageReading;
    }

    public void setBatterybankIPVoltageReading(SiteInfoStatusData batterybankIPVoltageReading) {
        this.batterybankIPVoltageReading = batterybankIPVoltageReading;
    }

    public SiteInfoStatusData getBatterybankOPVolatgeReading() {
        return batterybankOPVolatgeReading;
    }

    public void setBatterybankOPVolatgeReading(SiteInfoStatusData batterybankOPVolatgeReading) {
        this.batterybankOPVolatgeReading = batterybankOPVolatgeReading;
    }

    public SiteInfoStatusData getBatterybankAcceptanceStatus() {
        return batterybankAcceptanceStatus;
    }

    public void setBatterybankAcceptanceStatus(SiteInfoStatusData batterybankAcceptanceStatus) {
        this.batterybankAcceptanceStatus = batterybankAcceptanceStatus;
    }

    public SiteInfoStatusData getBatterybankOperationStatus() {
        return batterybankOperationStatus;
    }

    public void setBatterybankOperationStatus(SiteInfoStatusData batterybankOperationStatus) {
        this.batterybankOperationStatus = batterybankOperationStatus;
    }

    public SiteInfoStatusData getBatterybankMaintenanceVendor() {
        return batterybankMaintenanceVendor;
    }

    public void setBatterybankMaintenanceVendor(SiteInfoStatusData batterybankMaintenanceVendor) {
        this.batterybankMaintenanceVendor = batterybankMaintenanceVendor;
    }

    public SiteInfoStatusData getDgequipmentname() {
        return dgequipmentname;
    }

    public void setDgequipmentname(SiteInfoStatusData dgequipmentname) {
        this.dgequipmentname = dgequipmentname;
    }

    public SiteInfoStatusData getDgRatingCapacity() {
        return dgRatingCapacity;
    }

    public void setDgRatingCapacity(SiteInfoStatusData dgRatingCapacity) {
        this.dgRatingCapacity = dgRatingCapacity;
    }

    public SiteInfoStatusData getDgCanopyAvailable() {
        return dgCanopyAvailable;
    }

    public void setDgCanopyAvailable(SiteInfoStatusData dgCanopyAvailable) {
        this.dgCanopyAvailable = dgCanopyAvailable;
    }

    public SiteInfoStatusData getDgInstalledLocationType() {
        return dgInstalledLocationType;
    }

    public void setDgInstalledLocationType(SiteInfoStatusData dgInstalledLocationType) {
        this.dgInstalledLocationType = dgInstalledLocationType;
    }

    public SiteInfoStatusData getDgInstallationType() {
        return dgInstallationType;
    }

    public void setDgInstallationType(SiteInfoStatusData dgInstallationType) {
        this.dgInstallationType = dgInstallationType;
    }

    public SiteInfoStatusData getDgFuelType() {
        return dgFuelType;
    }

    public void setDgFuelType(SiteInfoStatusData dgFuelType) {
        this.dgFuelType = dgFuelType;
    }

    public SiteInfoStatusData getDgOwnerCompany() {
        return dgOwnerCompany;
    }

    public void setDgOwnerCompany(SiteInfoStatusData dgOwnerCompany) {
        this.dgOwnerCompany = dgOwnerCompany;
    }

    public SiteInfoStatusData getDgUserCompany() {
        return DgUserCompany;
    }

    public void setDgUserCompany(SiteInfoStatusData dgUserCompany) {
        DgUserCompany = dgUserCompany;
    }

    public SiteInfoStatusData getDgInstallationVendor() {
        return dgInstallationVendor;
    }

    public void setDgInstallationVendor(SiteInfoStatusData dgInstallationVendor) {
        this.dgInstallationVendor = dgInstallationVendor;
    }

    public SiteInfoStatusData getDgIPVoltageReading() {
        return dgIPVoltageReading;
    }

    public void setDgIPVoltageReading(SiteInfoStatusData dgIPVoltageReading) {
        this.dgIPVoltageReading = dgIPVoltageReading;
    }

    public SiteInfoStatusData getDgOPVolatgeReading() {
        return dgOPVolatgeReading;
    }

    public void setDgOPVolatgeReading(SiteInfoStatusData dgOPVolatgeReading) {
        this.dgOPVolatgeReading = dgOPVolatgeReading;
    }

    public SiteInfoStatusData getDgAcceptanceStatus() {
        return dgAcceptanceStatus;
    }

    public void setDgAcceptanceStatus(SiteInfoStatusData dgAcceptanceStatus) {
        this.dgAcceptanceStatus = dgAcceptanceStatus;
    }

    public SiteInfoStatusData getDgOperationStatus() {
        return dgOperationStatus;
    }

    public void setDgOperationStatus(SiteInfoStatusData dgOperationStatus) {
        this.dgOperationStatus = dgOperationStatus;
    }

    public SiteInfoStatusData getAcequipmentname() {
        return Acequipmentname;
    }

    public void setAcequipmentname(SiteInfoStatusData acequipmentname) {
        Acequipmentname = acequipmentname;
    }

    public SiteInfoStatusData getAcRatingCapacity() {
        return acRatingCapacity;
    }

    public void setAcRatingCapacity(SiteInfoStatusData acRatingCapacity) {
        this.acRatingCapacity = acRatingCapacity;
    }

    public SiteInfoStatusData getSmpsName() {
        return smpsName;
    }

    public void setSmpsName(SiteInfoStatusData smpsName) {
        this.smpsName = smpsName;
    }

    public SiteInfoStatusData getAcOwnerCompany() {
        return acOwnerCompany;
    }

    public void setAcOwnerCompany(SiteInfoStatusData acOwnerCompany) {
        this.acOwnerCompany = acOwnerCompany;
    }

    public SiteInfoStatusData getAcUserCompany() {
        return AcUserCompany;
    }

    public void setAcUserCompany(SiteInfoStatusData acUserCompany) {
        AcUserCompany = acUserCompany;
    }

    public SiteInfoStatusData getAcInstallationVendor() {
        return acInstallationVendor;
    }

    public void setAcInstallationVendor(SiteInfoStatusData acInstallationVendor) {
        this.acInstallationVendor = acInstallationVendor;
    }

    public SiteInfoStatusData getAcAcceptanceStatus() {
        return AcAcceptanceStatus;
    }

    public void setAcAcceptanceStatus(SiteInfoStatusData acAcceptanceStatus) {
        AcAcceptanceStatus = acAcceptanceStatus;
    }

    public SiteInfoStatusData getAcOperationStatus() {
        return acOperationStatus;
    }

    public void setAcOperationStatus(SiteInfoStatusData acOperationStatus) {
        this.acOperationStatus = acOperationStatus;
    }

    public SiteInfoStatusData getAcMaintenanceVendor() {
        return acMaintenanceVendor;
    }

    public void setAcMaintenanceVendor(SiteInfoStatusData acMaintenanceVendor) {
        this.acMaintenanceVendor = acMaintenanceVendor;
    }

    @SerializedName("AcMaintenanceVendor")
    @Expose
    private SiteInfoStatusData acMaintenanceVendor;
}
