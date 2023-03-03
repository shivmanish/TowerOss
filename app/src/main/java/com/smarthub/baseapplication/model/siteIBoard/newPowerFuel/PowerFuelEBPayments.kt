package com.smarthub.baseapplication.model.siteIBoard.newPowerFuel

data class PowerFuelEBPayments(
    val Amount: String,
    val BillNumber: String,
    val PaymentDate: String,
    val PaymentMode: Int,
    val PaymentRefNo: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)