package com.smarthub.baseapplication.model.siteIBoard.newNocAndComp

data class NocCompAllData(
    val ApplicationInitial: ArrayList<NocApplicationInitial>,
    val AuthorityDetail: ArrayList<NocAuthorityDetail>,
    val AuthorityFeePaymentDetail: ArrayList<NocAuthorityFeePaymentDetail>,
    val PODetail: ArrayList<NocPODetail>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)