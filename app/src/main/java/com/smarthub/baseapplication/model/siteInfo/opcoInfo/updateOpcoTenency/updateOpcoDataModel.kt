package com.smarthub.baseapplication.model.siteInfo.opcoInfo.updateOpcoTenency

import com.smarthub.baseapplication.utils.AppController

class updateOpcoDataModel(
    val Operator: updateOpcoDataItem?=null,
    var id: String = "448",
    var ownername: String = AppController.getInstance().ownerName,
)