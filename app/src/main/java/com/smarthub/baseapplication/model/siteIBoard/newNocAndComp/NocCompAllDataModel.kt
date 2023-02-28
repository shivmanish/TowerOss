package com.smarthub.baseapplication.model.siteIBoard.newNocAndComp

import com.google.gson.annotations.SerializedName

class NocCompAllDataModel{
    @SerializedName("NOCCompliance")
    var NOCCompliance : ArrayList<NocCompAllData>?=null
    @SerializedName("TotalCount")
    var TotalCount : Any?=null
    @SerializedName("Status")
    var Status : Any?=null
    @SerializedName("Error")
    var Error : Any?=null
}