package com.smarthub.baseapplication.network.pojo.site_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SiteInfoDropDownData {
    @SerializedName("BasicInfo")
    @Expose
    private BasicInfoModel basicInfoModel;


    public BasicInfoModel getBasicInfoModel() {
        return basicInfoModel;
    }

    public void setBasicInfoModel(BasicInfoModel basicInfoModel) {
        this.basicInfoModel = basicInfoModel;
    }
}
