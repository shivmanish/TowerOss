package com.smarthub.baseapplication.model.serviceRequest

data class SPApprovalAndSO(
    val SODetails: List<SODetail>,
    val SPApproval: List<SPApproval>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)