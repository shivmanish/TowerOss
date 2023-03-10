package com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment

data class UtilityEquipmentAllData(
    val CableDetail: ArrayList<UtilityCableDetail>,
    val UtilityEquipmentAC: ArrayList<UtilityEquipmentAC>,
    val UtilityEquipmentBatteryBank: ArrayList<UtilityEquipmentBatteryBank>,
    val UtilityEquipmentDG: ArrayList<UtilityEquipmentDG>,
    val UtilityEquipmentFireExtinguisher: ArrayList<UtilityEquipmentFireExtinguisher>,
    val UtilityEquipmentPowerDistributionBox: ArrayList<UtilityEquipmentPowerDistributionBox>,
    val UtilityEquipmentSmps: ArrayList<UtilityEquipmentSmp>,
    val UtilityEquipmentSurgeProtectionDevice: ArrayList<UtilityEquipmentSurgeProtectionDevice>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)