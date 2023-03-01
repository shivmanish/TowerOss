package com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra

import com.google.gson.annotations.SerializedName

class TowerCivilAllDataModel{
    @SerializedName("TowerAndCivilInfra")
    var TowerAndCivilInfra : ArrayList<NewTowerCivilAllData>?=null
    @SerializedName("TotalCount")
    var TotalCount : Any?=null
    @SerializedName("Status")
    var Status : Any?=null
    @SerializedName("Error")
    var Error : Any?=null
}