package com.smarthub.baseapplication.model.siteInfo.utilitiesEquip

data class UtililitiesEquipAllDadaItem(
    val AC: List<AcUtility>,
    val BatteryBank: List<BatteryBank>,
    val DCPowerDistribution: List<DCPowerDistribution>,
    val DG: List<Any>,
    val FireExtinguisher: List<Any>,
    val SurgeProtection: List<SurgeProtection>,
    val UtilitieSmps: List<UtilitieSmp>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)