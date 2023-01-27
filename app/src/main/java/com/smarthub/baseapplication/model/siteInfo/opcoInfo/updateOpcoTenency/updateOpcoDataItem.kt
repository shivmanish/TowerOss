package com.smarthub.baseapplication.model.siteInfo.opcoInfo.updateOpcoTenency

import com.smarthub.baseapplication.model.siteInfo.opcoInfo.Opcoinfo
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.PowerLoadData
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.RfAnteenaData
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.rfEquipmentData
import com.smarthub.baseapplication.utils.AppController

data class updateOpcoDataItem(
    var Backhaullink: List<Any>?=null,
    var Commercials: List<Any>?=null,
    var Opcoinfo: List<Opcoinfo>?=null,
    var PowerLoad: List<PowerLoadData>?=null,
    var RfAntena: List<RfAnteenaData>?=null,
    var RfEquipment: List<rfEquipmentData>?=null,
    var id: String ?= "448",
    var ownername: String = AppController.getInstance().ownerName,
)