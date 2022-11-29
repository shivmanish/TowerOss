package com.smarthub.baseapplication.model.siteInfo

data class GeoCondition(
    val Altitude: String,
    val Floodzone: List<Floodzone>,
    val Potentialthreat: List<Potentialthreat>,
    val Seismiczone: List<Seismiczone>,
    val TempratureZone: String,
    val Terraintype: List<Terraintype>,
    val Windzone: List<Windzone>,
    val createddate: String,
    val id: Int,
    val isActive: Boolean,
    val modifieddate: String
)