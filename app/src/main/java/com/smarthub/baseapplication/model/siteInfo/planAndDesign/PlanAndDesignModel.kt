package com.smarthub.baseapplication.model.siteInfo.planAndDesign

import com.google.gson.annotations.SerializedName

class PlanAndDesignModel {
    @SerializedName("finaldata")
    var item : List<PlanDesignAllData>?=null

    @SerializedName("error")
    var error : Any?=null
}