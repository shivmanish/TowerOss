package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SAcqFeasibilityDetail(
    var Acquisitiontype: ArrayList<Int>,
    var Area: String,
    var EquipmentRoom: Int,
    var ExecutiveName: String,
    var ExpectedPrice: String,
    var FiberLMCLaying: Int,
    var MarketPrice: String,
    var OverallFeasibility: Int,
    var OwnerMeter: Int,
    var Remark: String,
    var RequiredAreaAvailable: Int,
    var StatutoryPermission: Int,
    var SurveyDate: String,
    var TowerPoleType: ArrayList<Int>,
//    var created_at: String,
    var id: Int,
//    var isActive: Boolean,
//    var modified_at: String
)