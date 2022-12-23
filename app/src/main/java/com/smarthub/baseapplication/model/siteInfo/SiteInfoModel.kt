package com.smarthub.baseapplication.model.siteInfo

import com.google.gson.annotations.SerializedName

class SiteInfoModel{
    @SerializedName("finaldata")
    var item : ArrayList<BasicInfoModelItem>?=null

    @SerializedName("error")
    var error : Any?=null
}