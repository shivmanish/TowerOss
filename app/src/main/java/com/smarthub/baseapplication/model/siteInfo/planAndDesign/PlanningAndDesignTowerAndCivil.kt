package com.smarthub.baseapplication.model.siteInfo.planAndDesign

data class PlanningAndDesignTowerAndCivil(
    val Pole: List<Pole>,
    val Tower: List<Tower>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)