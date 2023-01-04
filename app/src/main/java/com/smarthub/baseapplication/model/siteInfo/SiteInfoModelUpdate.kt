package com.smarthub.baseapplication.model.siteInfo

import com.google.gson.annotations.SerializedName
import com.smarthub.baseapplication.model.siteInfo.success.SuccessList

class SiteInfoModelUpdate{
    @SerializedName("FinalData")
    var item : ArrayList<BasicInfoModelItem>?=null

    @SerializedName("error")
    var error : Any?=null

    @SerializedName("Status")
    var success : SuccessList?=null
}