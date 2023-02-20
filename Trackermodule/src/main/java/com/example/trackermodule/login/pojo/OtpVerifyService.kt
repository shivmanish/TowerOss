package com.example.patrollerapp.login.pojo

import com.example.patrollerapp.util.PatrollerPriference

data class OtpVerifyService(
    val AppVersion: String = PatrollerPriference.APP_VERSION,
    val device_id: String = "",
    val key: String = "patroller",
    val otp: String = "",
    val phone: String = "",
    val username: String = ""
)