package com.smarthub.baseapplication.model.siteInfo.opcoInfo.updateOpcoTenency

import com.smarthub.baseapplication.model.siteInfo.opcoInfo.Opcoinfo
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.PowerLoadData
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.RfAnteenaData
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.rfEquipmentData
import com.smarthub.baseapplication.utils.AppController

data class updateOpcoDataItem(
    var Backhaullink: List<Any>?=null,
    var Commercials: List<Any>?=null,
    var Opcoinfo: Opcoinfo?=null,
    var PowerLoad: List<PowerLoadData>?=null,
    var RfAntena: List<RfAnteenaData>?=null,
    var RfEquipment: rfEquipmentData?=null,
    var id: String ?= AppController.getInstance().siteid,
)