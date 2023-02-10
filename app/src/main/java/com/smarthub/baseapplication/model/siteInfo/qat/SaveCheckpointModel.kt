package com.smarthub.baseapplication.model.siteInfo.qat

import com.smarthub.baseapplication.utils.AppController

data class SaveCheckpointModel(
    val QAT: ArrayList<SaveCheckpointData>?= ArrayList(),
    val id: String ?=AppController.getInstance().siteid,
    val isActive: Boolean?=true
)
