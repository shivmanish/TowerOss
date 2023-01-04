package com.smarthub.baseapplication.model.siteInfo.planAndDesign

data class PlanningAndDesignPowerRequirement(
    val BatteryBackup: String,
    val MaxTotalPower: String,
    val PowerType: String,
    val Remark: String,
    val Voltage: String,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)