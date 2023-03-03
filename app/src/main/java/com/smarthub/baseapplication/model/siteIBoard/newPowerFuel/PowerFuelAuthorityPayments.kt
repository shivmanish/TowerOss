package com.smarthub.baseapplication.model.siteIBoard.newPowerFuel

data class PowerFuelAuthorityPayments(
    val Amount: String,
    val DemandReceiptDate: String,
    val DueDate: String,
    val PaymentStatus: List<Int>,
    val PaymentType: String,
    val Remark: String,
    val StatusDate: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)