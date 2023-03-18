package com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment

data class UtilityEquipmentPowerDistributionBox(
    val Equipment: ArrayList<UtilitySMPSEquipment>,
    val InstallationAndAcceptence: ArrayList<UtiltyInstallationAcceptence>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)