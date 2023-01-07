package com.smarthub.baseapplication.model.siteInfo.siteAgreements

data class SiteacquisitionPayment(
    val Amount: String,
    val Date: String,
    val DueDate: String,
    val InvoiceNumber: String,
    val LineItemNumber: String,
    val NetPayable: String,
    val PayeeName: String,
    val PaymentAmount: String,
    val PaymentDate: String,
    val Status: String,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)