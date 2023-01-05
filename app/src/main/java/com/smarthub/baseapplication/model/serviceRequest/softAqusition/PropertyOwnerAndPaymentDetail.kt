package com.smarthub.baseapplication.model.serviceRequest.softAqusition

data class PropertyOwnerAndPaymentDetail(
    val AccountName: String,
    val BankBranch: String,
    val BankIFSCCode: String,
    val GSTNumber: String,
    val PanNumber: String,
    val PayeeBank: String,
    val PayeeName: String,
    val PayeeStatus: String,
    val Seq: String,
    val Share: String,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)