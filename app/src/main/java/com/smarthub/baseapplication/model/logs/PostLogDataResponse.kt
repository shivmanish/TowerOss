package com.smarthub.baseapplication.model.logs

data class PostLogDataResponse(
    val ChangeLog: String,
    val error: String,
    val success: Boolean
)