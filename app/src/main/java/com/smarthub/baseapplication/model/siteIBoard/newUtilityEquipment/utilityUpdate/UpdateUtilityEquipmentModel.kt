package com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.utilityUpdate

import com.smarthub.baseapplication.utils.AppController

data class UpdateUtilityEquipmentModel(
    var UtilityEquipment: ArrayList<UpdateUtilityEquipmentAllData> ?=null,
    var id: String = AppController.getInstance().siteid,
    var ownername: String = AppController.getInstance().ownerName,
)