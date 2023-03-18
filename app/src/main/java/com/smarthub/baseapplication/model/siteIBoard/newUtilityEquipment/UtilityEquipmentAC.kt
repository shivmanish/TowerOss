package com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment

data class UtilityEquipmentAC(
    val ConsumableMaterial: ArrayList<UtilityConsumableMaterial>,
    val Equipment: ArrayList<UtilitySMPSEquipment>,
    val InstallationAndAcceptence: ArrayList<UtiltyInstallationAcceptence>,
    val PODetail: ArrayList<UtilityPoDetails>,
    val PreventiveMaintenance: ArrayList<UtilityPreventiveMaintenance>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)