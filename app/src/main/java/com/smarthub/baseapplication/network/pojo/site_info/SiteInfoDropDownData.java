package com.smarthub.baseapplication.network.pojo.site_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SiteInfoDropDownData {
    @SerializedName("BasicInfo")
    @Expose
    private BasicInfoModel basicInfoModel;

    @SerializedName("OperationalInfo")
    @Expose
    private OperationalInfoModel operationalInfo;

    @SerializedName("GeoCondition")
    @Expose
    private GeoConditionModel geoCondition;

    @SerializedName("SafetyAndAccess")
    @Expose
    private SafetyAndAccessModel safetyAndAccess;


    public BasicInfoModel getBasicInfoModel() {
        return basicInfoModel;
    }

    public void setBasicInfoModel(BasicInfoModel basicInfoModel) {
        this.basicInfoModel = basicInfoModel;
    }

    public OperationalInfoModel getOperationalInfo() {
        return operationalInfo;
    }

    public void setOperationalInfo(OperationalInfoModel operationalInfo) {
        this.operationalInfo = operationalInfo;
    }

    public GeoConditionModel getGeoCondition() {
        return geoCondition;
    }

    public void setGeoCondition(GeoConditionModel geoCondition) {
        this.geoCondition = geoCondition;
    }

    public SafetyAndAccessModel getSafetyAndAccess() {
        return safetyAndAccess;
    }

    public void setSafetyAndAccess(SafetyAndAccessModel safetyAndAccess) {
        this.safetyAndAccess = safetyAndAccess;
    }
}
