package com.smarthub.baseapplication.model.serviceRequest

data class RequesterInfo(
    var EmailID: String,
    var PhoneNumber: String,
    var Remarks: String,
    var RequesterExecutiveName: String,
    var created_at: String,
    var id: Int,
    var isActive: Boolean,
    var modified_at: String
)