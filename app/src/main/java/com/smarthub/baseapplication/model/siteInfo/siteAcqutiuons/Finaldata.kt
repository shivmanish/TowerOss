package com.example.example

import com.google.gson.annotations.SerializedName


data class Finaldata(

    @SerializedName("id") var id: String? = null,
    @SerializedName("isActive") var isActive: String? = null,
    @SerializedName("Siteacquisition") var Siteacquisition: ArrayList<Siteacquisition> = arrayListOf()

)