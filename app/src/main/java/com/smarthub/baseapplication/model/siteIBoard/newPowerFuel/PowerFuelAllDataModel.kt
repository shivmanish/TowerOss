package com.smarthub.baseapplication.model.siteIBoard.newPowerFuel

import com.google.gson.annotations.SerializedName

class PowerFuelAllDataModel{
    @SerializedName("PowerAndFuel")
    var PowerAndFuel : ArrayList<NewPowerFuelAllData>?=null
    @SerializedName("TotalCount")
    var TotalCount : Any?=null
    @SerializedName("Status")
    var Status : Any?=null
    @SerializedName("Error")
    var Error : Any?=null
}