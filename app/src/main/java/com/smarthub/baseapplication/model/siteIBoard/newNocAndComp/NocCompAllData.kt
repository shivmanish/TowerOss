package com.smarthub.baseapplication.model.siteIBoard.newNocAndComp

data class NocCompAllData(
    var ApplicationInitial: ArrayList<NocApplicationInitial>?=null,
    var AuthorityDetail: ArrayList<NocAuthorityDetail>?=null,
    var AuthorityFeePaymentDetail: ArrayList<NocAuthorityFeePaymentDetail>?=null,
    var PODetail: ArrayList<NocPODetail>?=null,
    var id: Int?=null,
    val created_at: String?=null,
    val isActive: Boolean?=null,
    val modified_at: String?=null
)