package com.smarthub.baseapplication.model.serviceRequest

data class PowerAndMCB(
    val MCB: List<MCB>,
    val Power: List<Power>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)