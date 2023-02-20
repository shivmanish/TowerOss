package com.example.patrollerapp.login.pojo

import com.example.patrollerapp.util.PatrollerPriference

data class OptSendService(
    val AppVersion: String = PatrollerPriference.APP_VERSION,
    val device_id: String = "",
    val key: String ="patroller",
    val phone: String = "",
    val signature: String   = "",
    val username: String = ""
)