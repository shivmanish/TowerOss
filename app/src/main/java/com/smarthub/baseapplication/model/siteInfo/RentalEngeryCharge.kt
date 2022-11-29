package com.smarthub.baseapplication.model.siteInfo

data class RentalEngeryCharge(
    val EbEffectiveDate: String,
    val EbPaymentfrequency: List<EbPaymentfrequency>,
    val Ebchargingmethod: Int,
    val Remarks: String,
    val RentalEffectiveDate: String,
    val RentalPaymentfrequency: List<RentalPaymentfrequency>,
    val Rentalchargingmethod: Int,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)