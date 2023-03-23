package com.smarthub.baseapplication.model.siteInfo.planAndDesign

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PlanAndDesignDataItem(
    @SerializedName("PlanningAndDesignNocAndComplianceRequirement")
    @Expose val NOCAndComp: List<NOCAndComp>,
    val PlanningAndDesignEquipRoomEquipmentRoom: List<PlanningAndDesignEquipRoomEquipmentRoom>,
    val PlanningAndDesignPowerRequirements: List<PlanningAndDesignPowerRequirement>,
    val PlanningAndDesignTowerAndCivil: List<PlanningAndDesignTowerAndCivil>,

    @SerializedName("PlanningAndDesignUtilityEquipment")
    @Expose val UtilityEquip: List<UtilityEquip>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)