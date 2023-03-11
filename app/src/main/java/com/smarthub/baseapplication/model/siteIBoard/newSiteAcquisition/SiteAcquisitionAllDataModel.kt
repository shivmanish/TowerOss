package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

import com.google.gson.annotations.SerializedName

class SiteAcquisitionAllDataModel{
    @SerializedName("SAcqSiteAcquisition")
    var SAcqSiteAcquisition : ArrayList<NewSiteAcquiAllData>?=null
    @SerializedName("TotalCount")
    var TotalCount : Any?=null
    @SerializedName("Status")
    var Status : Any?=null
    @SerializedName("Error")
    var Error : Any?=null
}