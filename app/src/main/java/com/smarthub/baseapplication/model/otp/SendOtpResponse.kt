package com.smarthub.baseapplication.model.otp

import com.google.gson.annotations.SerializedName

data class SendOtpResponse(
    @SerializedName("success") val success: Boolean?
)