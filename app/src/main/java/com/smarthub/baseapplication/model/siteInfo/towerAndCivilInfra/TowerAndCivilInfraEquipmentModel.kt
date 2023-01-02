package com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra

data class TowerAndCivilInfraEquipmentModel(
    val AuthorityPODetails: List<EquipmentModelAuthorityPODetails>,
    val TowerAndCivilInfraConsumable: List<EquipmentModelConsumable>,
    val TowerAndCivilInfraEquipment: List<EquipmentModelEquipmentInfo>,
    val TowerAndCivilInfraTowerInstallationAndAcceptance: List<EquipmentModelInstallationAndAcceptance>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)