package com.example.patrollerapp.homepage.pojo

import com.example.patrollerapp.util.PatrollerPriference

data class UserDetailsService(
    val AppVersion: String = PatrollerPriference.APP_VERSION,
    val device_id: String = "",
    val key: String = "patroller",
    val mobile: String = "",
    val signature: String = "NA"
)