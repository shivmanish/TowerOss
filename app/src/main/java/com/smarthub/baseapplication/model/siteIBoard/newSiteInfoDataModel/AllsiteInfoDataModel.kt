package com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel

import com.google.gson.annotations.SerializedName
import com.smarthub.baseapplication.utils.AppController

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

    var id: String = AppController.getInstance().siteid
    var ownername: String = AppController.getInstance().ownerName
}