package com.smarthub.baseapplication.model.siteInfo.newData

import com.google.gson.annotations.SerializedName
import com.smarthub.baseapplication.model.siteInfo.BasicInfoModelItem

class SiteInfoModelNew{
    @SerializedName("FinalData")
    var item : ArrayList<BasicInfoModelItem>?=null

    @SerializedName("error")
    var error : Any?=null
}