package com.smarthub.baseapplication.model.logs

import com.smarthub.baseapplication.utils.AppController

data class PostLogData(
    var ChangeLog: ArrayList<ChangeLog>?=ArrayList(),
    var id: String?="448",
    var ownername: String?=AppController.getInstance().ownerName
)