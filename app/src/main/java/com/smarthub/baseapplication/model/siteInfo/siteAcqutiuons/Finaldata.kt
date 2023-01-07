package com.example.example

import com.google.gson.annotations.SerializedName
import com.smarthub.baseapplication.model.siteInfo.siteAcqutiuons.Siteacquisition


data class Finaldata(

    @SerializedName("id") var id: String? = null,
    @SerializedName("isActive") var isActive: String? = null,
    @SerializedName("Siteacquisition") var Siteacquisition: ArrayList<Siteacquisition> = arrayListOf()

)