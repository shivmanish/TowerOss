package com.smarthub.baseapplication.model.serviceRequest

data class SRDetails(
    val Area: String,
    val Circle: String,
    val CityOrTown: String,
    val ExpectedDate: String,
    val HubSite: String,
    val OpcoSiteName: String,
    val OpcoSiteType: String,
    val Pincode: String,
    val Priority: String,
    val RequestDate: String,
    val SRStatus: String,
    val SRType: String,
    val SearchRadius: String,
    val created_at: String,
    val id: String,
    val isActive: String,
    val locLatitude: String,
    val locLongitude: String,
    val modified_at: String
)