package com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra

data class TowerAndCivilInfraTower(
    val ConsumableMaterial: ArrayList<TwrCivilConsumableMaterial>,
    val InstallationAndAcceptence: List<TwrInstallationAndAcceptence>,
    val PODetail: ArrayList<TwrCivilPODetail>,
    val PreventiveMaintenance: ArrayList<PreventiveMaintenance>,
    val TowerAndCivilInfraTowerTowerDetail: List<TwrCivilInfraTowerDetail>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)