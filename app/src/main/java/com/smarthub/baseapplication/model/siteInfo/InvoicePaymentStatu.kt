package com.smarthub.baseapplication.model.siteInfo

data class InvoicePaymentStatu(
    val Amount: Int,
    val DueDate: String,
    val InvoiceDate: String,
    val InvoiceNumber: String,
    val InvoiceaAttachments: Any,
    val Invoicestatus: List<Invoicestatu>,
    val LineItemNumber: Int,
    val NetPayable: Int,
    val PaymentAmount: Int,
    val PaymentDate: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)