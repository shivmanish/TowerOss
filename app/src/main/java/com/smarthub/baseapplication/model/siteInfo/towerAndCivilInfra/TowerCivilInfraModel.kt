package com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra

import com.google.gson.annotations.SerializedName
class TowerCivilInfraModel {
    @SerializedName("finaldata")
    var item : List<TowerCivilInfraAllData>?=null

    @SerializedName("error")
    var error : Any?=null
}