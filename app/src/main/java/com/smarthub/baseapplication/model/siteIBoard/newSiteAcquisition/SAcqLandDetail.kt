package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SAcqLandDetail(
    var LandType: ArrayList<Int>,
    var PropertyType: Int,
    var SiteDemarcation: Int,
    var SoilType: ArrayList<Int>,
    var Terraintype: ArrayList<Int>,
//    var created_at: String,
    var id: Int?=null,
//    var isActive: Boolean,
//    var modified_at: String
)