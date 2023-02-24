package com.smarthub.baseapplication.model.siteInfo.newSiteInfoDataModel

data class GeoConditionData(
    val Altitude: Int,
    val Climatezone: List<Any>,
    val Floodzone: List<Int>,
    val Potentialthreat: List<Int>,
    val Seismiczone: List<Int>,
    val TempRangeMax: Any,
    val TempRangeMin: Any,
    val Terraintype: List<Int>,
    val Windzone: List<Int>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)