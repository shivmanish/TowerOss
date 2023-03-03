package com.smarthub.baseapplication.model.siteIBoard.newPowerFuel

data class PowerConnectionAllData(
    val ConsumableMaterial: ArrayList<PowerConsumableMaterial>,
    val InstallationAndAcceptence: ArrayList<PowerInstallationAndAcceptence>,
    val PODetail: ArrayList<PowerFuelPODetail>,
    val PowerAndFuelEBConnectionEBDetail: ArrayList<PowerConnectionDetail>,
    val PowerAndFuelEBConnectionPayment: ArrayList<PowerFuelAuthorityPayments>,
    val PowerAndFuelEBConnectionTariffDetail: ArrayList<PowerFuelTariffDetails>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)