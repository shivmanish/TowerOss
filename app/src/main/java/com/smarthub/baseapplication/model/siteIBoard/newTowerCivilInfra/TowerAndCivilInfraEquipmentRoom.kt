package com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra

data class TowerAndCivilInfraEquipmentRoom(
    val ConsumableMaterial: List<TwrCivilConsumableMaterial>,
    val InstallationAndAcceptence: List<TwrInstallationAndAcceptence>,
    val PODetail: List<TwrCivilPODetail>,
    val PreventiveMaintenance: List<PreventiveMaintenance>,
    val TowerAndCivilInfraEquipmentRoomEquipmentRoomDetail: List<TwrCivilEquipmentRoomDetail>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)