package com.smarthub.baseapplication.network.pojo.site_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SiteInfoDropDownData {
    @SerializedName("BasicInfo")
    @Expose
    private BasicInfoModelDropDown basicInfoModelDropDown;

    @SerializedName("OperationalInfo")
    @Expose
    private OperationalInfoModel operationalInfo;

    @SerializedName("GeoCondition")
    @Expose
    private GeoConditionModel geoCondition;

    @SerializedName("SafetyAndAccess")
    @Expose
    private SafetyAndAccessModel safetyAndAccess;

    @SerializedName("Siteacquisition")
    @Expose
    private AcuationsLeaseModel acquistionMode;

    @SerializedName("Opcoinfo")
    @Expose
    private OpcoinfoModel opcoinfo;

    public BasicInfoModelDropDown getBasicInfoModel() {
        return basicInfoModelDropDown;
    }


    public void setBasicInfoModel(BasicInfoModelDropDown basicInfoModelDropDown) {
        this.basicInfoModelDropDown = basicInfoModelDropDown;
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

    public AcuationsLeaseModel getAcquistionMode() {
        return acquistionMode;
    }

    public void setAcquistionMode(AcuationsLeaseModel acquistionMode) {
        this.acquistionMode = acquistionMode;
    }

    public OpcoinfoModel getOpcoinfo() {
        return opcoinfo;
    }

    public void setOpcoinfo(OpcoinfoModel opcoinfo) {
        this.opcoinfo = opcoinfo;
    }
  }
