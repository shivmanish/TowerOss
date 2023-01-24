package com.smarthub.baseapplication.model.siteInfo

import com.smarthub.baseapplication.utils.AppController

data class SiteInfoParam(
    val fields: List<String>,
    val id: Int,
    val ownername: String?=AppController.getInstance().ownerName
)