package com.smarthub.baseapplication.model.siteInfo.planAndDesign

data class UtilityEquip(
    val AC: List<AC>,
    val BatteryBank: List<BatteryBank>,
    val DCDB: List<DCDB>,
    val DG: List<DG>,
    val FireExtinguisher: List<FireExtinguisher>,
    val SMPS: List<SMPS>,
    val SurgeProtectionDevice: List<SurgeProtectionDevice>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)