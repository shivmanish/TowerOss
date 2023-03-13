package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SAcqPODetail(
    var POAmount: String,
    var PODate: String,
    var POItem: String,
    var POLineNumber: Int,
    var PONumber: String,
    var Remark: String,
    var VendorCode: String,
    var VendorCompany: ArrayList<Int>,
//    var created_at: String,
    var id: Int?=null
//    var isActive: Boolean,
//    var modified_at: String
)