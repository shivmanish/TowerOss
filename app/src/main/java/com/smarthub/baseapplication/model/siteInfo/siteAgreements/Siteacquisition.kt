package com.smarthub.baseapplication.model.siteInfo.siteAgreements

data class Siteacquisition(
    val SiteacquisitionAgreements: List<SiteacquisitionAgreement>,
    val SiteacquisitionPayment: List<SiteacquisitionPayment>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)