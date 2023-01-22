package com.smarthub.baseapplication.model.siteInfo.nocAndCompModel

data class NocAndCompAllDataItem(
    val ApplicationInitial: List<ApplicationInitial>,
    val AuthorityDetails: List<AuthorityDetails>,
    val AuthorityFeePaymentDetails: List<Any>,
    val AuthorityPODetails: List<Any>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)