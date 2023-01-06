package com.smarthub.baseapplication.ui.fragments.powerAndFuel.pojo

import java.io.Serializable

data class PowerAndFuel(
    val PowerAndFuelEBBills: List<PowerAndFuelEBBill>,
    val PowerAndFuelEBConnection: List<PowerAndFuelEBConnection>,
    val PowerAndFuelEBPayment: List<PowerAndFuelEBPayment>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
): Serializable
