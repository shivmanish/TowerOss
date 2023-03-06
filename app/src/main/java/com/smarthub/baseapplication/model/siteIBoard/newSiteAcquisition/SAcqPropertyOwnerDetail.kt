package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SAcqPropertyOwnerDetail(
    val Address: String,
    val EmailId: String,
    val OwnerName: String,
    val PhoneNumber: String,
    val PropertyOwnership: List<Int>,
    val Remark: String,
    val Share: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)