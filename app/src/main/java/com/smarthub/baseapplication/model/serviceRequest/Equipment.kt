package com.smarthub.baseapplication.model.serviceRequest

data class Equipment(
    val CabinetSize: String,
    val EquipmentWeight: String,
    val InputPower: String,
    val MaxPowerRating: String,
    val OperatingTemp: String,
    val Technology: String,
    val Voltage: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)