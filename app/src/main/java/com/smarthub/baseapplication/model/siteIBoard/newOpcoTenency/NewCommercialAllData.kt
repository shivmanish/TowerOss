package com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency

data class NewCommercialAllData(
    val ColocationFee: ArrayList<CommercialColocationFee>,
    val InvoicePaymentStatus: ArrayList<CommercialInvoicePaymentStatu>,
    val OpcoContactDetail: ArrayList<CommercialOpcoContactDetail>,
    val SODetail: ArrayList<CommercialSODetail>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)