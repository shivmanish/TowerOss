package com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.updateNocComp

import com.smarthub.baseapplication.utils.AppController

data class UpdateNocCompModel(
    var NOCCompliance: ArrayList<UpdateNocCompAllData> ?=null,
    var id: String = AppController.getInstance().siteid,
    var ownername: String = AppController.getInstance().ownerName,
)