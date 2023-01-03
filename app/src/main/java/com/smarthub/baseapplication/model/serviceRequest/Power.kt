package com.smarthub.baseapplication.model.serviceRequest

data class Power(
    val AdditionalBatteryBackup: String,
    val AdditionalPower: String,
    val BasicBatteryBackup: String,
    val BasicSitePowerRating: String,
    val MaxTotalPower: String,
    val PowerRequirement: String,
    val PowerType: String,
    val Remark: String,
    val Timeline: String,
    val TotalBatteryBackup: String,
    val TotalSitePower: String,
    val Voltage: String,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)