package com.smarthub.baseapplication.model.siteInfo.planAndDesign

import com.google.gson.annotations.SerializedName

class PlanAndDesignModel {
    @SerializedName("PlanningAndDesign")
    val PlanningAndDesign: ArrayList<PlanAndDesignDataItem> ?=null

    @SerializedName("error")
    var error : Any?=null
}