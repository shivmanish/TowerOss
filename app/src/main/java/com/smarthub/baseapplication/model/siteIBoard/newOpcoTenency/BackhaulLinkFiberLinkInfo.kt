package com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency

data class BackhaulLinkFiberLinkInfo(
    val BackhaulType: List<Int>,
    val FarEndSiteName: String,
    val LinkBandwidth: String,
    val LinkId: String,
    val LinkName: String,
    val OperationStatus: List<Int>,
    val OperationalDate: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)