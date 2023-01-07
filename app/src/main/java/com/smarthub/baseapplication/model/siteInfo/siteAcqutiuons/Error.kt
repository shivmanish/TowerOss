package com.example.example

import com.google.gson.annotations.SerializedName


data class Error(

    @SerializedName("Siteacquisition")
    var Siteacquisition: ArrayList<String> = arrayListOf()

)