package com.smarthub.baseapplication.model.serviceRequest.opcoTssr

data class SectorsOrCellDetail(
    val AntennaHeight: String,
    val AzimuthOrOrientation: String,
    val Feasibility: String,
    val Frequency: String,
    val Obstructions: String,
    val SerialNo: String,
    val Shape: String,
    val Size: String,
    val TRXCount: String,
    val Technology: String,
    val Type: String,
    val Weight: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)