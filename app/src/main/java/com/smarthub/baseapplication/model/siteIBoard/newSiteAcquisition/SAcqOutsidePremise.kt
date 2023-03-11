package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SAcqOutsidePremise(
    val Direction: List<Int>,
    val DistanceFromBoundry: String,
    val ExternalStructureType: List<Int>,
    val Height: String,
    val Remark: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)