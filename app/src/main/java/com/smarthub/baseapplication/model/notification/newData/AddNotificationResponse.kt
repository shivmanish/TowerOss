package com.smarthub.baseapplication.model.notification.newData

import com.google.gson.annotations.SerializedName

data class AddNotificationResponse(
    @SerializedName("error")
    val error: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)