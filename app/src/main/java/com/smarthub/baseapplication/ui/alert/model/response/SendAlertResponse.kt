package com.smarthub.baseapplication.ui.alert.model.response

data class SendAlertResponse(
    val `data`: List<Data>,
    val error: String,
    val message: String,
    val success: Boolean
)