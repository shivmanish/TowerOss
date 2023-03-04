package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SAcqLandDetail(
    val LandType: List<Int>,
    val PropertyType: Int,
    val SiteDemarcation: Int,
    val SoilType: List<Int>,
    val Terraintype: List<Int>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)