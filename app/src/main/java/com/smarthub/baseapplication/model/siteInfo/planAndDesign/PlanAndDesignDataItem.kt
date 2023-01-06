package com.smarthub.baseapplication.model.siteInfo.planAndDesign

data class PlanAndDesignDataItem(
    val NOCAndComp: List<NOCAndComp>,
    val PlanningAndDesignEquipRoomEquipmentRoom: List<PlanningAndDesignEquipRoomEquipmentRoom>,
    val PlanningAndDesignPowerRequirements: List<PlanningAndDesignPowerRequirement>,
    val PlanningAndDesignTowerAndCivil: List<PlanningAndDesignTowerAndCivil>,
    val UtilityEquip: List<UtilityEquip>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)