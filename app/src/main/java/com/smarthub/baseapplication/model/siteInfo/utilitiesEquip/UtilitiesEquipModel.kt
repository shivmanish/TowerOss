package com.smarthub.baseapplication.model.siteInfo.utilitiesEquip

import com.google.gson.annotations.SerializedName

class UtilitiesEquipModel {
    @SerializedName("finaldata")
    var item : List<UtilitiesEquipAllData>?=null

    @SerializedName("error")
    var error : Any?=null
}