package com.smarthub.baseapplication.model.siteIBoard.newPowerFuel

data class PowerFuelTariffDetails(
    val AverageConsumableUnit: String,
    val TariffRate: String,
    val TarrifEffectiveDate: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)