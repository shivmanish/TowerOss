package com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel

data class SaftyAccessData(
    val CautionSignage: List<Int>,
    val DangerSignage: List<Int>,
    val GateAndFence: Int,
    val NearByFireStation: String,
    val NearByFireStationNumber: String,
    val NearByPoliceStation: String,
    val NearByPoliceStationNumber: String,
    val Physicalsecurity: List<Int>,
    val SiteAccessWay: Int,
    val Siteaccessmethodology: String,
    val Videomonitoring: List<Int>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)