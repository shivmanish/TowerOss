package com.smarthub.baseapplication.model.siteIBoard.newNocAndComp

data class NocAuthorityFeePaymentDetail(
    val Amount: String,
    val ApplicationNo: String,
    val PaymentMode: Int,
    val PaymentStatus: List<Int>,
    val Type: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)