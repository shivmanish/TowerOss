package com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel

data class SaftyAccessData(
    val CautionSignage: Int,
    val DangerSignage: Int,
    val GateAndFence: Int,
    val NearByFireStation: String,
    val NearByFireStationDistance: Any,
    val NearByFireStationNumber: String,
    val NearByPoliceStation: String,
    val NearByPoliceStationDistance: Any,
    val NearByPoliceStationNumber: String,
    val Physicalsecurity: List<Int>,
    val SiteAccessWay: Int,
    val Siteaccessmethodology: String,
    val Videomonitoring: Int,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)