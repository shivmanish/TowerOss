package com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency

data class CommercialInvoicePaymentStatu(
    val Amount: String,
    val DueDate: String,
    val InvoiceDate: String,
    val InvoiceNumber: String,
    val InvoiceaAttachments: Any,
    val LineItemNumber: Int,
    val NetPayable: String,
    val PaymentAmount: String,
    val PaymentDate: String,
    val Status: List<Int>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)