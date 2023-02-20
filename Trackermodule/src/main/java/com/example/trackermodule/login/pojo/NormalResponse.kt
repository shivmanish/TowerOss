package com.example.patrollerapp.login.pojo

import com.google.gson.annotations.SerializedName

data class NormalResponse(
    @SerializedName("Success") val Success: String,
    @SerializedName("success") val success: Boolean,
    @SerializedName("Message") val Message: String
) {

}