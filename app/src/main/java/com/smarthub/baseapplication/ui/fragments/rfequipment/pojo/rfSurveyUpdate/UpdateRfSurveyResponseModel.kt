package com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.rfSurveyUpdate

import com.google.gson.annotations.SerializedName

class UpdateRfSurveyResponseModel{
    @SerializedName("data")
    val data: Data?=null
    @SerializedName("error")
    val error: Error?=null
    @SerializedName("status")
    val status: Status?=null
}