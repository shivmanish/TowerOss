package com.smarthub.baseapplication.model.siteIBoard.newPowerFuel

data class NewPowerFuelAllData(
    val PowerAndFuelEBBil: ArrayList<PowerBills>,
    val PowerAndFuelEBConnection: ArrayList<PowerConnectionAllData>,
    val PowerAndFuelEBPayment: ArrayList<PowerFuelEBPayments>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)