package com.smarthub.baseapplication.model.siteInfo.NocAndCompModel

data class NocAndCompAllDataItem(
    val ApplicationInitial: List<ApplicationInitial>,
    val AuthorityDetails: List<Any>,
    val AuthorityFeePaymentDetails: List<Any>,
    val AuthorityPODetails: List<Any>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)