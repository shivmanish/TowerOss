package com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra

data class TowerAndCivilInfraTowerModel(
    val towerModelAuthorityPODetails: List<TowerModelAuthorityPODetail>,
    val TowerAndCivilInfraConsumable: List<TowerModelConsumable>,
    val TowerAndCivilInfraTowerInstallationAndAcceptance: List<TowerModelTowerInstallationAndAcceptance>,
    val TowerTowerAndCivilInfraTower: List<TowerModelTowerInfo>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)