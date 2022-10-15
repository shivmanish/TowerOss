package com.smarthub.baseapplication.model.register

import com.google.gson.annotations.SerializedName

data class RegstationResponse(
    @SerializedName("status") val status: String?,
    @SerializedName("Errors") val Errors: String?
)
// status can have two value {"status": "success"} ,,{"status": "Failed", "Errors": "This is already registred"}
