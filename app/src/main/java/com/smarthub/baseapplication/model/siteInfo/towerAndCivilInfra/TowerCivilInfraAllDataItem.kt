package com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra

data class TowerCivilInfraAllDataItem(
    val TowerAndCivilInfraEarthingModel: List<TowerAndCivilInfraEarthingModel>,
    val TowerAndCivilInfraEquipmentModel: List<TowerAndCivilInfraEquipmentModel>,
    val TowerAndCivilInfraPoleModel: List<TowerAndCivilInfraPoleModel>,
    val TowerAndCivilInfraTowerModel: List<TowerAndCivilInfraTowerModel>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)