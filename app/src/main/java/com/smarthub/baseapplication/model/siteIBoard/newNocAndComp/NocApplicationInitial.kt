package com.smarthub.baseapplication.model.siteIBoard.newNocAndComp

data class NocApplicationInitial(
    val ApplicationDate: String,
    val ApplicationNumber: String,
    val AuthorityApplicationType: List<Int>,
    val Category: Int,
    val DocumentNo: String,
    val ExpiryDate: String,
    val IssueDate: String,
    val PaymentStatus: List<Any>,
    val SrNumber: Int,
    val StatusDate: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)