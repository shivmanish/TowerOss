package com.smarthub.baseapplication.model.siteInfo.newSiteInfoDataModel

import com.google.gson.annotations.SerializedName

class AllsiteInfoDataModel{
    @SerializedName("Basicinfo")
    var Basicinfo : ArrayList<BasicInfoData>?=null
    @SerializedName("OperationalInfo")
    var OperationalInfo : ArrayList<OprationalInfoData>?=null
    @SerializedName("GeoCondition")
    var GeoCondition : ArrayList<GeoConditionData>?=null
    @SerializedName("SafetyAndAccess")
    var SafetyAndAccess : ArrayList<SaftyAccessData>?=null
    @SerializedName("Siteaddress")
    var Siteaddress : ArrayList<SiteAddressData>?=null

    @SerializedName("Error")
    var Error : Any?=null
}