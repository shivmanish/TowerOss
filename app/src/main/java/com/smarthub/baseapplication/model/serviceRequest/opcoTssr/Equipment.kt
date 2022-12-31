package com.smarthub.baseapplication.model.serviceRequest.opcoTssr

data class Equipment(
    val OpcoTSSREquipmentTable: List<OpcoTSSREquipmentTable>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)