package com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency

data class CommercialColocationFee(
    val AdditionalIPFeePm: String,
    val AnchorRent: String,
    val BasicIPFeePm: String,
    val BatteryBackupIPFee: String,
    val DiscountPercentageorAmount: String,
    val DiscountType: Int,
    val EBChargingMethod: Int,
    val EscalationPercentage: String,
    val FeeRevisionPeriod: String,
    val FiberLMCIPFee: String,
    val NetPayableRentPm: String,
    val PremiumAmountPm: String,
    val PremiumApplicable: Int,
    val Remark: String,
    val SharingRent: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)