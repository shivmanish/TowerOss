package com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency

data class NewOpcoInfoData(
    val OperationsTeam: ArrayList<OpcoInfoOperationsTeam>,
    val SRInfo01: ArrayList<OpcoInfoSiteDetails>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)