package com.smarthub.baseapplication.model

import com.google.gson.annotations.SerializedName

data class CommonResponse(
    @SerializedName("status")
    val status: String?,
    @SerializedName("Errors")
    val Errors: String?
)