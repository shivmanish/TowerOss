package com.smarthub.baseapplication.model.siteInfo.qat

import com.google.gson.annotations.SerializedName

class QatModel {
    @SerializedName("finaldata")
    var item : List<QatAllData>?=null

    @SerializedName("error")
    var error : Any?=null
}