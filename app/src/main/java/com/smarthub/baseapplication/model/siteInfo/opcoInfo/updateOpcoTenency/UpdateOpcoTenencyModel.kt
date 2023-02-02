package com.smarthub.baseapplication.model.siteInfo.opcoInfo.updateOpcoTenency

import com.smarthub.baseapplication.utils.AppController

data class UpdateOpcoTenencyModel(
    var Operator: updateOpcoDataItem?=null,
    var id: String = AppController.getInstance().siteid,
    var ownername: String = AppController.getInstance().ownerName,
)