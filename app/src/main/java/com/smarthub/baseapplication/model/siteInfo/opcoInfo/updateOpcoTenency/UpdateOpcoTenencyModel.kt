package com.smarthub.baseapplication.model.siteInfo.opcoInfo.updateOpcoTenency

import com.smarthub.baseapplication.utils.AppController

data class UpdateOpcoTenencyModel(
    val Operator: updateOpcoDataItem?=null,
    var id: String = "448",
    var ownername: String = AppController.getInstance().ownerName,
)