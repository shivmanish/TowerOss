package com.smarthub.baseapplication.model.siteInfo

data class SafetyAndAcces(
    val CautionSignage: String?,
    val DangerSignage: String?,
    val GateAndFence: String?,
    val NearByFireStation: String,
    val NearByFireStationNumber: String,
    val NearByPoliceStation: String,
    val NearByPoliceStationNumber: String,
    val Physicalsecurity: String?,
    val SiteAccessArea: String?,
    val Siteaccess: String?,
    val Siteaccessmethodology: String,
    val Videomonitoring: String?,
    val createddate: String,
    val id: Int,
    val isActive: Boolean,
    val modifieddate: String
)