package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate

import com.smarthub.baseapplication.utils.AppController

data class UpdateSiteAcqModel(
    var SAcqSiteAcquisition: ArrayList<UpdateSiteAcquiAllData> ?=null,
    var id: String = AppController.getInstance().siteid,
    var ownername: String = AppController.getInstance().ownerName,
)