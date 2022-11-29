package com.smarthub.baseapplication.model.siteInfo

data class Basicinfo(
    val Buildingtype: List<Buildingtype>,
    val Locationzone: List<Locationzone>,
    val MaintenancePoint: List<MaintenancePoint>,
    val Projectname: List<Projectname>,
    val Sitecategory: List<Sitecategory>,
    val Siteownership: List<Siteownership>,
    val Sitestatus: List<Sitestatu>,
    val Sitetype: List<Sitetype>,
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
    val sitePicture: Any,
    val siteaddress: String
)