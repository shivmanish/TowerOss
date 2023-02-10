package com.smarthub.baseapplication.model.siteInfo.qat

import com.smarthub.baseapplication.utils.AppController

data class SaveCheckpointModel(
    var QAT: ArrayList<SaveCheckpointData>?= ArrayList(),
    var id: String ?=AppController.getInstance().siteid,
    var ownername: String?=AppController.getInstance().ownerName
)
