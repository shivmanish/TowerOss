package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SAcqPayeeAccountDetail(
    var AccountNumber: String,
    var BankBranch: String,
    var BankIFSCode: String,
    var GSTIN: String,
    var PAN: String,
    var PayeeBank: String,
    var PayeeName: String,
    var PayeeStatus: Int,
    var Share: String,
//    var created_at: String,
    var id: Int?=null,
//    var isActive: Boolean,
//    var modified_at: String
)