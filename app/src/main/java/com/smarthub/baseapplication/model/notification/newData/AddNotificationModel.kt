package com.smarthub.baseapplication.model.notification.newData

import com.smarthub.baseapplication.utils.AppController

data class AddNotificationModel(
    var Description: String?=null,
    var Files: String?="",
    var Flag: String?=null,
    var Send: String?="",
    var Titles: String?=null,
    var Username: ArrayList<String>?=ArrayList<String>(),
    var ownername: String= AppController.getInstance().ownerName
)