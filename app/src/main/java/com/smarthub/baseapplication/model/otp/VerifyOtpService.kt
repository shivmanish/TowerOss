package com.smarthub.baseapplication.model.otp

data class VerifyOtpService(
    val device_id: String,
    val key: String,
    val mobile: String,
    val otp: String,
    val appVersion: String
)