package com.smarthub.baseapplication.model.serviceRequest

data class SPSubmissionToOpco(
    val EmailID: String,
    val MobileNumber: String,
    val SPSubmissionDate: String,
    val SubmittedBy: String,
    val SubmittedTo: String,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String,
    val name: String
)