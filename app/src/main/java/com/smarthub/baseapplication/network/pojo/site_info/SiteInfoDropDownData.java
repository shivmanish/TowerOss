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

/*    @SerializedName("Utilities")
    @Expose
    private UtilitesNocModel utilities;*/
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

    public RfEquipmentModel getRfEquipment() {
        return rfEquipment;
    }

    public void setRfEquipment(RfEquipmentModel rfEquipment) {
        this.rfEquipment = rfEquipment;
    }

    public BackhaullinkModel getBackhaullink() {
        return backhaullink;
    }

    public void setBackhaullink(BackhaullinkModel backhaullink) {
        this.backhaullink = backhaullink;
    }

    public RfAntenaModel getRfAntena() {
        return rfAntena;
    }

    public void setRfAntena(RfAntenaModel rfAntena) {
        this.rfAntena = rfAntena;
    }

    public ComercialModel getComercial() {
        return comercial;
    }

    public void setComercial(ComercialModel comercial) {
        this.comercial = comercial;
    }

    @SerializedName("comercial")
    @Expose
    private ComercialModel comercial;





}
