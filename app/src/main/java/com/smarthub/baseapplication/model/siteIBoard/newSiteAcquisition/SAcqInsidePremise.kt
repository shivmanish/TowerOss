package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SAcqInsidePremise(
    var Direction: ArrayList<Int>,
    var DistanceFromCentre: String,
    var ExternalStructureType: ArrayList<Int>,
    var Height: String,
    var LocationType: Int,
    var remark: String,
//    var created_at: String,
    var id: Int?=null,
//    var isActive: Boolean,
//    var modified_at: String
)