package com.smarthub.baseapplication.model.logs

import com.smarthub.baseapplication.utils.AppController

data class PostLogData(
    val ChangeLog: ArrayList<ChangeLog>?=ArrayList(),
    val id: String?="448",
    val ownername: String?=AppController.getInstance().ownerName
)