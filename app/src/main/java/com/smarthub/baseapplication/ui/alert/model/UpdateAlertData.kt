package com.smarthub.baseapplication.ui.alert.model

import com.smarthub.baseapplication.utils.AppController

data class UpdateAlertData(
    val ActionStatus: String,
    val TentativeDate: String,
    val FullDetails: String,
    val update: String,
    val ownerName : String ?= AppController.getInstance().ownerName,

)