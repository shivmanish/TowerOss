package com.smarthub.baseapplication.model.otp

data class SendOtpService(
    val device_id: String,
    val key: String,
    val mobile: String,
    val signature: String,
    val username: String
)