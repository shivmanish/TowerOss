package com.smarthub.baseapplication.model.serviceRequest.opcoTssr

data class BackhaulFeasibility(
    val BackHaulFeasibilityRemark: String,
    val BackHaulNodeType: String,
    val Fiber: List<Fiber>,
    val MicrowaveOrUBR: List<MicrowaveOrUBR>,
    val OffSetPoleRequired: String,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)