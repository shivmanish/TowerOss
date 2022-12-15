package com.smarthub.baseapplication.model.serviceRequest

data class RequesterInfo(
    val EmailID: String,
    val PhoneNumber: String,
    val Remarks: String,
    val RequesterExecutiveName: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)