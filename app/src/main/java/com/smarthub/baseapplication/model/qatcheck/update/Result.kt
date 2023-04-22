package com.smarthub.baseapplication.model.qatcheck.update

import com.google.gson.annotations.SerializedName
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.QatMainAllData

class Result {
//    @SerializedName("finaldata")
//    var item : List<QatMainAllData>?=null

    @SerializedName("result")
    var result : List<QatMainAllData>?=null

    @SerializedName("error")
    var error : Any?=null
}