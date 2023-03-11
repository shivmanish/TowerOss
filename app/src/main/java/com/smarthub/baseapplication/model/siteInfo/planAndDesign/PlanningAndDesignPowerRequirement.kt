package com.smarthub.baseapplication.model.siteInfo.planAndDesign

data class PlanningAndDesignPowerRequirement(
    val PowerConnectionType: List<Int>,
    val PowerRating: String,
    val PowerSupplier: String,
    val PowerType: Int,
    val Remark: String,
    val VoltageMax: String,
    val VoltageMin: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)