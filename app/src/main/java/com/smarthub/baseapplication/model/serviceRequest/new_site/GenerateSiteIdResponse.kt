package com.smarthub.baseapplication.model.serviceRequest.new_site

import com.smarthub.baseapplication.utils.AppController

data class GenerateSiteIdResponse(
    val Generatid: String,
    var ownername: String = AppController.getInstance().ownerName,
)