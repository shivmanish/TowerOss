package com.smarthub.baseapplication.model.siteInfo

data class SiteBasicinfo(
    var Buildingtype: String?,
    var Locationzone: String?,
    var MaintenancePoint: String?,
    var Projectname: String?,
    var Sitecategory: String,
    var Siteownership: String?,
    var Sitestatus: String?,
    var Sitetype: String?,
    var aliasName: String,
    var created_at: String,
    var id: Int,
    var isActive: Boolean,
    var locLatitude: String,
    var locLongitude: String,
    var modified_at: String,
    var siteID: String,
    var siteInChargeName: String,
    var siteInChargeNumber: String,
    var siteLayout: Any,
    var siteName: String,
    var National: String,
    var Region: String,
    var State: String,
    var sitePicture: Any,
    var siteaddress: String
)