package com.smarthub.baseapplication.model.serviceRequest

data class SRDetails(
    var Area: String,
    var Circle: String,
    var CityOrTown: String,
    var ExpectedDate: String,
    var HubSite: Boolean,
    var OpcoSiteName: String,
    var OpcoSiteType: String,
    var Pincode: String,
    var Priority: String,
    var RequestDate: String,
    var RequesterCompany: String,
    var SRStatus: String,
    var SRType: String,
    var SearchRadius: String,
    var Technology: String,
    var created_at: String,
    var id: Int,
    var isActive: Boolean,
    var locLatitude: String,
    var locLongitude: String,
    var modified_at: String
)