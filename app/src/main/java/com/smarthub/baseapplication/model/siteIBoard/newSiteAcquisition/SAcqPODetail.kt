package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SAcqPODetail(
    val POAmount: String,
    val PODate: String,
    val POItem: String,
    val POLineNumber: Int,
    val PONumber: String,
    val Remark: String,
    val VendorCode: String,
    val VendorCompany: List<Int>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)