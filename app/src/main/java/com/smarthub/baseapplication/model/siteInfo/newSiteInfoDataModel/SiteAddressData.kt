package com.smarthub.baseapplication.model.siteInfo.newSiteInfoDataModel

data class SiteAddressData(
    val address1: String,
    val address2: String,
    val city: List<Any>,
    val created_at: String,
    val district: List<Any>,
    val id: Int,
    val isActive: Boolean,
    val landmark: String,
    val locLatitude: String,
    val locLongitude: String,
    val modified_at: String,
    val pincode: String,
    val state: List<Any>,
    val tehsil: List<Any>
)