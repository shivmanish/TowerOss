package com.smarthub.baseapplication.ui.alert.model.request

import com.smarthub.baseapplication.utils.AppController

data class GetUserList(
    val department: String  = "D1",
    val ownerName: String  = AppController.getInstance().ownerName
)