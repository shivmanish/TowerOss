package com.smarthub.baseapplication.model.serviceRequest

data class SiteDetail(
    val Address: String,
    val ExistingTenants: String,
    val Nominallatitude: String,
    val Nominallongitude: String,
    val OpcoSiteID: String,
    val OpcoSiteName: String,
    val ProjectName: String,
    val Sitelatitude: String,
    val Sitelongitude: String,
    val TocoSiteName: String,
    val TocoUID: String,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)