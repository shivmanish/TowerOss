package com.smarthub.baseapplication.model.siteIBoard.newsstSbc

import com.google.gson.annotations.SerializedName

class SstSbcAllDataModel{
    @SerializedName("SstSbc")
    var SstSbc : ArrayList<SstSbcAllData>?=null
    @SerializedName("TotalCount")
    var TotalCount : Any?=null
    @SerializedName("Status")
    var Status : Any?=null
    @SerializedName("Error")
    var Error : Any?=null
}