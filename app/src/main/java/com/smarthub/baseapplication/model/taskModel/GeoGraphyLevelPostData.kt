package com.smarthub.baseapplication.model.taskModel

import com.smarthub.baseapplication.utils.AppController

data class GeoGraphyLevelPostData(
    val Metadata: String,
    val ownercode: String ?= AppController.getInstance().ownerName,
    val ownername: String ?= AppController.getInstance().ownerName
)