package com.smarthub.baseapplication.model.serviceRequest

data class SPApproval(
    val ApprovedBy: String,
    val ApproverEmailID: String,
    val SPApprovalDate: String,
    val SPSubmissionDate: String,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)