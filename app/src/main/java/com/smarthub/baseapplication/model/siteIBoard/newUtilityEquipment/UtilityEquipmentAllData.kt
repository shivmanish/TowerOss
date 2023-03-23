package com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment

data class UtilityEquipmentAllData(
    val CableDetail: ArrayList<UtilityCableDetail>,
    val UtilityEquipmentAC: ArrayList<UtilityEquipmentAC>?=null,
    val UtilityEquipmentBatteryBank: ArrayList<UtilityEquipmentBatteryBank>?=null,
    val UtilityEquipmentDG: ArrayList<UtilityEquipmentDG>?=null,
    val UtilityEquipmentFireExtinguisher: ArrayList<UtilityEquipmentFireExtinguisher>,
    val UtilityEquipmentPowerDistributionBox: ArrayList<UtilityEquipmentPowerDistributionBox>?=null,
    val UtilityEquipmentSmps: ArrayList<UtilityEquipmentSmp>?=null,
    val UtilityEquipmentSurgeProtectionDevice: ArrayList<UtilityEquipmentSurgeProtectionDevice>?=null,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)