package com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency

data class NewRfEquipmentData(
    val CableDetail: ArrayList<CableDetailsData>,
    val Equipment01: ArrayList<EquipmentData>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)