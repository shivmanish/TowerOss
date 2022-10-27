package com.smarthub.baseapplication.network.pojo.site_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SiteInfoDropDownData {
    @SerializedName("BasicInfo")
    @Expose
    private BasicInfoModel basicInfoModel;

    @SerializedName("OperationalInfo")
    @Expose
    private BasicInfoModel operationalInfo;


    public BasicInfoModel getBasicInfoModel() {
        return basicInfoModel;
    }

    public void setBasicInfoModel(BasicInfoModel basicInfoModel) {
        this.basicInfoModel = basicInfoModel;
    }

    public BasicInfoModel getOperationalInfo() {
        return operationalInfo;
    }

    public void setOperationalInfo(BasicInfoModel operationalInfo) {
        this.operationalInfo = operationalInfo;
    }
}
