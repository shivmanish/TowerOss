package com.smarthub.baseapplication.model.serviceRequest.opcoTssr

data class PowerRequirement(
    var BatteryBackupRequired: String,
    var InputType: String,
    var InputVoltage: String,
    var MaxTotalPower: String,
    var created_at: String,
    var id: String,
    var isActive: String,
    var modified_at: String
)