package com.smarthub.baseapplication.model.siteInfo.siteInfoData

import com.google.gson.annotations.SerializedName

class SiteInfoDataModel{
    @SerializedName("finaldata")
    var item : List<SiteInfoAllData>?=null

    @SerializedName("error")
    var error : Any?=null
}