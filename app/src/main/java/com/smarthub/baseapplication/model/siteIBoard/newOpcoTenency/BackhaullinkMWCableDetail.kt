package com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency

data class BackhaullinkMWCableDetail(
    val CableName: List<Int>,
    val CableType: String,
    val Length: String,
    val Remark: String,
    val SerialNumber: String,
    val LinkId: String,
    val UsedFor: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)