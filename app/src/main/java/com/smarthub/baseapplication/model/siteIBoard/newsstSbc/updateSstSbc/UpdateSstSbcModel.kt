package com.smarthub.baseapplication.model.siteIBoard.newsstSbc.updateSstSbc

import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcAllData
import com.smarthub.baseapplication.utils.AppController

data class UpdateSstSbcModel(
    var SstSbc: ArrayList<SstSbcAllData> ?=null,
    var id: String = AppController.getInstance().siteid,
    var ownername: String = AppController.getInstance().ownerName,
)