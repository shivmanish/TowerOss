package com.smarthub.baseapplication.model.serviceRequest

data class RadioAntenna(
    val AntennaCount: String,
    val AntennaHeight: String,
    val AntennaShape: List<Any>,
    val AntennaSize: String,
    val AntennaTotalWeight: String,
    val Technology: List<Any>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)