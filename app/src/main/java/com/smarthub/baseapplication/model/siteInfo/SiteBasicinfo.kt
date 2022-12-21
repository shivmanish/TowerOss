package com.smarthub.baseapplication.model.siteInfo

data class SiteBasicinfo(
    val Buildingtype: String?,
    val Locationzone: String?,
    val MaintenancePoint: String?,
    val Projectname: String?,
    val Sitecategory: String,
    val Siteownership: String?,
    val Sitestatus: String?,
    val Sitetype: String?,
    val aliasName: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val locLatitude: String,
    val locLongitude: String,
    val modified_at: String,
    val siteID: String,
    val siteInChargeName: String,
    val siteInChargeNumber: String,
    val siteLayout: Any,
    val siteName: String,
    val National: String,
    val Region: String,
    val State: String,
    val sitePicture: Any,
    val siteaddress: String
)