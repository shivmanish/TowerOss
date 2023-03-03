package com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra

data class TowerAndCivilInfraEarthing(
    val ConsumableMaterial: List<TwrCivilConsumableMaterial>,
    val InstallationAndAcceptence: List<TwrInstallationAndAcceptence>,
    val PODetail: List<TwrCivilPODetail>,
    val PreventiveMaintenance: List<PreventiveMaintenance>,
    val TowerAndCivilInfraEarthingEarthingDetail: List<TwrCivilInfraEarthingDetail>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)