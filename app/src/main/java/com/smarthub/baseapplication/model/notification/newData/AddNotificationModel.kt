package com.smarthub.baseapplication.model.notification.newData

import com.smarthub.baseapplication.utils.AppController

data class AddNotificationModel(
    var Description: String?=null,
    val Files: String?=null,
    val Flag: String?=null,
    val Send: String?=null,
    val Titles: String?=null,
    val Username: List<String>?=ArrayList<String>(),
    val ownername: String= AppController.getInstance().ownerName
)