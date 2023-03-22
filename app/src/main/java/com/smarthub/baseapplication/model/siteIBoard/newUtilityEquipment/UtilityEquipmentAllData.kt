package com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment

data class UtilityEquipmentAllData(
    val CableDetail: ArrayList<UtilityCableDetail>,
    val UtilityEquipmentAC: ArrayList<UtilityEquipmentAC>,
    val UtilityEquipmentBatteryBank: ArrayList<UtilityEquipmentBatteryBank>?=null,
    val UtilityEquipmentDG: ArrayList<UtilityEquipmentDG>?=null,
    val UtilityEquipmentFireExtinguisher: ArrayList<UtilityEquipmentFireExtinguisher>,
    val UtilityEquipmentPowerDistributionBox: ArrayList<UtilityEquipmentPowerDistributionBox>,
    val UtilityEquipmentSmps: ArrayList<UtilityEquipmentSmp>?=null,
    val UtilityEquipmentSurgeProtectionDevice: ArrayList<UtilityEquipmentSurgeProtectionDevice>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)