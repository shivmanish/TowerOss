package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SAcqPropertyOwnerDetail(
    var Address: String,
    var EmailId: String,
    var OwnerName: String,
    var PhoneNumber: String,
    var PropertyOwnership: ArrayList<Int>,
    var remark: String,
    var Share: String,
//    var created_at: String,
    var id: Int?=null,
//    var isActive: Boolean,
//    var modified_at: String
)