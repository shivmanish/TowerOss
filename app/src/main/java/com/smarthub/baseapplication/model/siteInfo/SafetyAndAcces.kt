package com.smarthub.baseapplication.model.siteInfo

data class SafetyAndAcces(
    var CautionSignage: String?,
    var DangerSignage: String?,
    var GateAndFence: String?,
    var NearByFireStation: String,
    var NearByFireStationNumber: String,
    var NearByPoliceStation: String,
    var NearByPoliceStationNumber: String,
    var Physicalsecurity: String?,
    var SiteAccessArea: String?,
    var Siteaccess: String?,
    var Siteaccessmethodology: String,
    var Videomonitoring: String?,
    var createddate: String,
    var id: Int,
    var isActive: Boolean,
    var modifieddate: String
)