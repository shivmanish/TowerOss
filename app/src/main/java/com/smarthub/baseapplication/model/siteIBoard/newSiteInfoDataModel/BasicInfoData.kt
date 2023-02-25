package com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel

data class BasicInfoData(
    val Acquisitiontype: List<Int>,
    val Buildingtype: List<Int>,
    val Costcentre: List<Int>,
    val MaintenancePoint: List<Int>,
    val National: List<Int>,
    val Projectname: List<Int>,
    val Region: List<Int>,
    val Sitecategory: List<Int>,
    val Siteownership: List<Int>,
    val Sitestatus: List<Int>,
    val Sitetype: List<Int>,
    val State: List<Int>,
    val aliasName: String,
    val area: List<Any>,
    val cluster: List<Any>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String,
    val siteID: String,
    val siteLayout: Any,
    val siteName: String,
    val sitePicture: Any
)