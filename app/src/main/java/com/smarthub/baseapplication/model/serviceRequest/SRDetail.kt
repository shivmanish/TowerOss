package com.smarthub.baseapplication.model.serviceRequest

data class SRDetail(
    val Area: String,
    val Circle: String,
    val CityOrTown: String,
    val ExpectedDate: String,
    val HubSite: Boolean,
    val OpcoSiteName: String,
    val OpcoSiteType: String,
    val Pincode: String,
    val Priority: String,
    val RequestDate: String,
    val RequesterCompany: List<RequesterCompany>,
    val SRStatus: String,
    val SRType: String,
    val SearchRadius: String,
    val Technology: List<Technology>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val locLatitude: String,
    val locLongitude: String,
    val modified_at: String
)