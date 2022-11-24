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

    @SerializedName("Siteacquisition")
    @Expose
    private AcuationsLeaseModel acquistionMode;


    @SerializedName("Opcoinfo")
    @Expose
    private OpcoinfoModel opcoinfo;


  /*  @SerializedName("AcquistionMode")
    @Expose
    private AcuationsLeaseModel acuationsLeaseModel;
    @Expose
    private UtilitesNocModel utilitesNocModel;
    @SerializedName("Utilities")
    @Expose
    private UtilitesNocModel utilities;
    @SerializedName("Opcoinfo")
    @Expose
    private OpcoinfoModel opcoinfo;
    @SerializedName("RfEquipment")
    @Expose
    private RfEquipmentModel rfEquipment;
    @SerializedName("Backhaullink")
    @Expose
    private BackhaullinkModel backhaullink;
    @SerializedName("RfAntena")
    @Expose
    private RfAntenaModel rfAntena;
    @SerializedName("comercial")
    @Expose
    private ComercialModel comercial;
*/
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
