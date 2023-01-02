package com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra

data class TowerAndCivilInfraPoleModel(
    val towerModelAuthorityPODetails: List<PoleModelAuthorityPODetails>,
    val TowerAndCivilInfraConsumable: List<PoleModelConsumable>,
    val TowerAndCivilInfraTowerInstallationAndAcceptance: List<PoleModelInstallationAndAcceptance>,
    val TowerTowerAndCivilInfraTower: List<PoleModelTowerInfo>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)