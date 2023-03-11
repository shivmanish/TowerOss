package com.smarthub.baseapplication.model.siteIBoard.newPowerFuel

data class PowerConnectionDetail(
    val AverageAvailibility: String,
    val ConnectedLoad: String,
    val ConsumerNumber: String,
    val ElectricitySupplier: String,
    val MeterLocationMark: String,
    val MeterSerialNumber: String,
    val MeterType: Int,
    val PowerConnectionType: List<Int>,
    val PowerRating: String,
    val PowerType: Int,
    val Remark: String,
    val VoltageMax: String,
    val VoltageMin: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)