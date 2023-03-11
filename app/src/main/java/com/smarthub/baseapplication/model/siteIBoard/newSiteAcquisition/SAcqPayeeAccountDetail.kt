package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SAcqPayeeAccountDetail(
    val AccountNumber: String,
    val BankBranch: String,
    val BankIFSCode: String,
    val GSTIN: String,
    val PAN: String,
    val PayeeBank: String,
    val PayeeName: String,
    val PayeeStatus: Int,
    val Share: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)