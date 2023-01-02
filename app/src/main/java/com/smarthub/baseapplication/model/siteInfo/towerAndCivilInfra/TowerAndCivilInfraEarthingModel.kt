package com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra

data class TowerAndCivilInfraEarthingModel(
    val towerModelAuthorityPODetails: List<EarthingModelAuthorityPODetails>,
    val TowerAndCivilInfraConsumable: List<EarthingModelConsumable>,
    val TowerAndCivilInfraEarthing: List<EarthingModelEarthingInfo>,
    val TowerAndCivilInfraTowerInstallationAndAcceptance: List<EarthingModelInstallationAndAcceptance>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)