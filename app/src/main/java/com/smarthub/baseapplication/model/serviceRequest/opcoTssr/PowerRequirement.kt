package com.smarthub.baseapplication.model.serviceRequest.opcoTssr

data class PowerRequirement(
    val BatteryBackupRequired: String,
    val InputType: String,
    val InputVoltage: String,
    val MaxTotalPower: String,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)