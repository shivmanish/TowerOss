package com.smarthub.baseapplication.model.siteInfo.utilitiesEquip

data class UtililitiesEquipAllDadaItem(
    val AC: List<Any>,
    val BatteryBank: List<Any>,
    val DCPowerDistribution: List<Any>,
    val DG: List<Any>,
    val FireExtinguisher: List<Any>,
    val SurgeProtection: List<Any>,
    val UtilitieSmps: List<UtilitieSmp>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)