package com.smarthub.baseapplication.model.siteInfo.qat.qat_main

import com.google.gson.annotations.SerializedName

class QatMainModel {
    @SerializedName("finaldata")
    var item : List<QatMainAllData>?=null

    @SerializedName("error")
    var error : Any?=null
}