package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SAcqBuildingDetail(
    val BuildingBuildType: Int,
    val BuildingHeight: String,
    val BuildingType: List<Int>,
    val ConstructionYear: Int,
    val NoOfFloors: Int,
    val PropertyType: Int,
    val TypicalFloorArea: String,
//    val created_at: String,
    val id: Int?=null,
//    val isActive: Boolean,
//    val modified_at: String
)