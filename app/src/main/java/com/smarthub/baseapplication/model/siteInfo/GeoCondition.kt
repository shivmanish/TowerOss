package com.smarthub.baseapplication.model.siteInfo

data class GeoCondition(
    val Altitude: String?,
    val Floodzone: String?,
    val Potentialthreat: String?,
    val Seismiczone: String?,
    val TempratureZone: String,
    val Terraintype: String?,
    val Windzone: String?,
    val createddate: String,
    val id: Int,
    val isActive: Boolean,
    val modifieddate: String
)