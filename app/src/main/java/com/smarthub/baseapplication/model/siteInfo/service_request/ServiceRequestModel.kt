package com.smarthub.baseapplication.model.siteInfo.service_request

import com.google.gson.annotations.SerializedName
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataNew

class ServiceRequestModel{
    @SerializedName("ServiceRequestMain")
    val ServiceRequestMain: ServiceRequestAllData?=null

    @SerializedName("error")
    var error : Any?=null
}