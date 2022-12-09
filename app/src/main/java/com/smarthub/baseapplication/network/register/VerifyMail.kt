package com.smarthub.baseapplication.network.register

data class VerifyMail(
    val checkemaildomain: String,
    val email: String,
    val ownername: String
)