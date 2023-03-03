package com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra

data class NewTowerCivilAllData(
    val TowerAndCivilInfraEarthing: ArrayList<TowerAndCivilInfraEarthing>,
    val TowerAndCivilInfraEquipmentRoom: ArrayList<TowerAndCivilInfraEquipmentRoom>,
    val TowerAndCivilInfraPole: ArrayList<TowerAndCivilInfraPole>,
    val TowerAndCivilInfraTower: ArrayList<TowerAndCivilInfraTower>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)