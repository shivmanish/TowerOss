package com.smarthub.baseapplication.model.siteInfo.planAndDesign

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PlanningAndDesignTowerAndCivil(

   @SerializedName("PlanningAndDesignTowerAndCivilPole")
   @Expose val Pole: List<PoleDataNew>,
    @SerializedName("PlanningAndDesignTowerAndCivilTower")
    @Expose val Tower: List<PlanningAndDesignTowerAndCivilTower>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)