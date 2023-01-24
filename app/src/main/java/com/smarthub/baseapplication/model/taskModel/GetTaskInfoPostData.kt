package com.smarthub.baseapplication.model.taskModel

import com.smarthub.baseapplication.utils.AppController

data class GetTaskInfoPostData(
    val gettaskdata: Int,
    val ownername: String ?= AppController.getInstance().ownerName
)