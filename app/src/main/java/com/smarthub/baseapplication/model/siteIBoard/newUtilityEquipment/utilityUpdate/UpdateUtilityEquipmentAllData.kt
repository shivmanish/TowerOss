package com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.utilityUpdate

import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.*

data class UpdateUtilityEquipmentAllData(
    var CableDetail: ArrayList<UtilityCableDetail>?=null,
    var UtilityEquipmentAC: ArrayList<UtilityEquipmentAC>?=null,
    var UtilityEquipmentBatteryBank: ArrayList<UtilityEquipmentBatteryBank>?=null,
    var UtilityEquipmentDG: ArrayList<UtilityEquipmentDG>?=null,
    var UtilityEquipmentFireExtinguisher: ArrayList<UtilityEquipmentFireExtinguisher>?=null,
    var UtilityEquipmentPowerDistributionBox: ArrayList<UtilityEquipmentPowerDistributionBox>?=null,
    var UtilityEquipmentSmps: ArrayList<UtilityEquipmentSmp>?=null,
    var UtilityEquipmentSurgeProtectionDevice: ArrayList<UtilityEquipmentSurgeProtectionDevice>?=null,
    var id: Int?=null,

)