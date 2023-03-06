package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SAcqFeasibilityDetail(
    val Acquisitiontype: List<Int>,
    val Area: String,
    val EquipmentRoom: Int,
    val ExecutiveName: String,
    val ExpectedPrice: String,
    val FiberLMCLaying: Int,
    val MarketPrice: String,
    val OverallFeasibility: Int,
    val OwnerMeter: Int,
    val Remark: String,
    val RequiredAreaAvailable: Int,
    val StatutoryPermission: Int,
    val SurveyDate: String,
    val TowerPoleType: List<Int>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)