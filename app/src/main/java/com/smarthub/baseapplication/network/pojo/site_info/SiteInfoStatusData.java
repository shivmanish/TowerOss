package com.smarthub.baseapplication.network.pojo.site_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smarthub.baseapplication.model.dropdown.DropDownItem;

import java.util.List;

public class SiteInfoStatusData {

    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("data")
    @Expose
    private List<DropDownItem> data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<DropDownItem> getData() {
        return data;
    }

    public void setData(List<DropDownItem> data) {
        this.data = data;
    }
}
