package com.smarthub.baseapplication.model.serviceRequest

data class Equipment(
    val CabinetSize: String,
    val EquipmentWeight: String,
    val InputPower: String,
    val MaxPowerRating: String,
    val OperatingTemp: String,
    val Voltage: String,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)