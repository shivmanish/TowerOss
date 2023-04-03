package com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel

data class BasicInfoData(
    val Acquisitiontype: List<Int>,
    val Buildingtype: List<Int>,
    val Costcentre: List<Int>,
    val MaintenancePoint: BasicInfoNew?=null,
    val National: BasicInfoNew?=null,
    val Opcositetype: List<Int>?=null,
    val Projectname: List<Int>,
    val Region: BasicInfoNew?=null,
    val Sitecategory: List<Int>,
    val Siteownership: List<Int>,
    val Sitestatus: List<Int>,
    val State: BasicInfoNew?=null,
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