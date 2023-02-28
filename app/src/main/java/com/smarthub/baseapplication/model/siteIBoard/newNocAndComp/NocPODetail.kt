package com.smarthub.baseapplication.model.siteIBoard.newNocAndComp

data class NocPODetail(
    val POAmount: String,
    val PODate: String,
    val POItem: Any,
    val POLineNo: Int,
    val PONumber: Any,
    val Remark: Any,
    val VendorCode: Any,
    val VendorCompany: List<Any>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)