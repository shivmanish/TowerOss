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

    public AcuationsLeaseModel getSafetyAndAccess() {
        return safetyAndAccess;
    }

    public void setSafetyAndAccess(AcuationsLeaseModel safetyAndAccess) {
        this.safetyAndAccess = safetyAndAccess;
    }

    public AcuationsLeaseModel getAcquistionMode() {
        return acquistionMode;
    }

    public void setAcquistionMode(AcuationsLeaseModel acquistionMode) {
        this.acquistionMode = acquistionMode;
    }

    @SerializedName("SafetyAndAccess")
    @Expose
    private AcuationsLeaseModel safetyAndAccess;
    @SerializedName("AcquistionMode")
    @Expose
    private AcuationsLeaseModel acquistionMode;




}
