package com.smarthub.baseapplication.ui.alert.model.response

import com.smarthub.baseapplication.ui.alert.model.newData.NewData

data class SendAlertResponseNew(
    val data: List<NewData>,
    val error: String,
    val message: String,
    val success: Boolean
)