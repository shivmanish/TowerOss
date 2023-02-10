package com.smarthub.baseapplication.model.siteInfo.qat.qat_main

data class PunchpointData(
    val AssignedTo: String,
    val AssigneeDepartment: String,
    val ClosedBy: String,
    val QATIssueType: String,
    val QATObservation: String,
    val QATPunchPointStatus: String,
    val Recommendation: String,
    val Resolution: String,
    val ResolveDateTime: String,
    val TargetDateTime: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)