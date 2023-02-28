package com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency

import com.google.gson.annotations.SerializedName

class OpcoTenencyAllDataModel{
    @SerializedName("Operator")
    var Operator : ArrayList<OpcoTenencyAllData>?=null
    @SerializedName("TotalCount")
    var TotalCount : Any?=null
    @SerializedName("Status")
    var Status : Any?=null
    @SerializedName("Error")
    var Error : Any?=null
}