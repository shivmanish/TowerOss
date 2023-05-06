package com.smarthub.baseapplication.model.siteIBoard.newPowerFuel

data class PowerFuelPODetail(
    var POAmount: String="0",
    var PODate: String,
    var POItem: String,
    var POLineNo: Int,
    var PONumber: String,
    var remark: String,
    var VendorCode: String,
    var VendorCompany: ArrayList<Int>,
    var id: Int?=null,
    var created_at: String?=null,
    var isActive: Boolean?=null,
    var modified_at: String?=null
)