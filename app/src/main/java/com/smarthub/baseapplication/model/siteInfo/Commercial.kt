package com.smarthub.baseapplication.model.siteInfo

data class Commercial(
    val InvoicePaymentStatus: List<InvoicePaymentStatu>,
    val OpcoContactDetails: List<OpcoContactDetail>,
    val RentalEngeryCharges: List<RentalEngeryCharge>,
    val Sodetails: List<Sodetail>,
    val colocationFee: List<ColocationFee>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)