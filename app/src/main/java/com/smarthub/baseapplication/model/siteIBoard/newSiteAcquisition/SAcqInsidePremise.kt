package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SAcqInsidePremise(
    val Direction: List<Int>,
    val DistanceFromCentre: String,
    val ExternalStructureType: List<Int>,
    val Height: String,
    val LocationType: Int,
    val Remark: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)