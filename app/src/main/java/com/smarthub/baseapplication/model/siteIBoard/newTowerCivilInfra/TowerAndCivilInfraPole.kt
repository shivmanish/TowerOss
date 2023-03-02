package com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra

data class TowerAndCivilInfraPole(
    val ConsumableMaterial: ArrayList<TwrCivilConsumableMaterial>,
    val InstallationAndAcceptence: ArrayList<TwrInstallationAndAcceptence>,
    val PODetail: ArrayList<TwrCivilPODetail>,
    val PreventiveMaintenance: ArrayList<PreventiveMaintenance>,
    val TowerAndCivilInfraPolePoleDetail: ArrayList<TwrCivilInfraPoleDetail>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)