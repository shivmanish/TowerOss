package com.smarthub.baseapplication.model.siteIBoard.newPowerFuel

data class PowerFuelBills(
    val Amount: String,
    val BillMonth: String,
    val BillNumber: String,
    val DueDate: String,
    val PaymentStatus: List<Int>,
    val StatusDate: String,
    val UnitConsumed: Int,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)