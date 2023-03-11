package com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment

import com.google.gson.annotations.SerializedName

class UtilityEquipmentAllDataModel{
    @SerializedName("UtilityEquipment")
    var UtilityEquipment : ArrayList<UtilityEquipmentAllData>?=null
    @SerializedName("TotalCount")
    var TotalCount : Any?=null
    @SerializedName("Status")
    var Status : Any?=null
    @SerializedName("Error")
    var Error : Any?=null
}