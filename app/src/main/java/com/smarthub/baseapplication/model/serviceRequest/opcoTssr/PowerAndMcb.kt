package com.smarthub.baseapplication.model.serviceRequest.opcoTssr

data class PowerAndMcb(
    val MCBRequirements: List<MCBRequirement>,
    val PowerRequirements: List<PowerRequirement>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)