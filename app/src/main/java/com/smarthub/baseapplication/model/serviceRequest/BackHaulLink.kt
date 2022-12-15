package com.smarthub.baseapplication.model.serviceRequest

data class BackHaulLink(
    val SRBackHaulLinksFiberPair: List<Any>,
    val ServiceRequestBackHaulLinkTableMW: List<Any>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)