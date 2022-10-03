package com.smarthub.baseapplication.model.login

data class TokenResponse(
    val access: String,
    val refresh: String
)