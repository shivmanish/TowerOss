package com.example.patrollerapp.login.pojo

data class LoginWithPasscode(
    val AppVersion: String = "1.0.3",
    val device_id: String = "",
    val loginpin: String = "",
    val number: String = ""
)