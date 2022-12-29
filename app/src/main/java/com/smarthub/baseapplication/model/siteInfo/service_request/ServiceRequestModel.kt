package com.smarthub.baseapplication.model.siteInfo.service_request

import com.google.gson.annotations.SerializedName
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataNew

class ServiceRequestModel{
    @SerializedName("FinalData")
    var item : List<ServiceRequestAllDataNew>?=null

    @SerializedName("error")
    var error : Any?=null
}