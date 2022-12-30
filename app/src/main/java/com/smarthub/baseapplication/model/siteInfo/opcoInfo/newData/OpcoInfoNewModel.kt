package com.smarthub.baseapplication.model.siteInfo.opcoInfo.newData

import com.google.gson.annotations.SerializedName
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataNew

class OpcoInfoNewModel{
    @SerializedName("finaldata")
    var item : List<OpcoInfoAllDataNew>?=null

    @SerializedName("error")
    var error : Any?=null
}