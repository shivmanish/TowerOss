package com.smarthub.baseapplication.model.dropdown

import com.google.gson.annotations.SerializedName

data class DropDownList(
    @SerializedName("status")
    val status: String?,
    @SerializedName("Errors")
    val Errors: String?,
    @SerializedName("data")
    val data: List<DropDownItem>?
)