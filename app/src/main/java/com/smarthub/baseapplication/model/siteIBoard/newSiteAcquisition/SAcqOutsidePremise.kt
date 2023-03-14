package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SAcqOutsidePremise(
    var Direction: ArrayList<Int>,
    var DistanceFromBoundry: String,
    var ExternalStructureType: ArrayList<Int>,
    var Height: String,
    var Remark: String,
//    var created_at: String,
    var id: Int?=null,
//    var isActive: Boolean,
//    var modified_at: String
)