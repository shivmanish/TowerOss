package com.smarthub.baseapplication.model.siteInfo.powerFuel

import com.google.gson.annotations.SerializedName

class PowerAndFuelModel {
    @SerializedName("finaldata")
    var item : List<PowerFuelAllData>?=null

    @SerializedName("error")
    var error : Any?=null
}