package com.smarthub.baseapplication.model.siteInfo.siteAgreements

data class PODetail(
    val POAmount: String="0",
    val PODate: String,
    val POLineNumber: String,
    val PONumber: String,
    val VendorName: String,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)