package com.smarthub.baseapplication.model.siteInfo

data class SafetyAndAcces(
    val CautionSignage: List<CautionSignage>,
    val DangerSignage: List<DangerSignage>,
    val GateAndFence: List<GateAndFence>,
    val NearByFireStation: String,
    val NearByFireStationNumber: String,
    val NearByPoliceStation: String,
    val NearByPoliceStationNumber: String,
    val Physicalsecurity: List<Physicalsecurity>,
    val SiteAccessArea: List<SiteAccessArea>,
    val Siteaccess: List<Siteacces>,
    val Siteaccessmethodology: String,
    val Videomonitoring: List<Videomonitoring>,
    val createddate: String,
    val id: Int,
    val isActive: Boolean,
    val modifieddate: String
)