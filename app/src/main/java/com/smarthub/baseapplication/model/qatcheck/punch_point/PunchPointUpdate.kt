package com.smarthub.baseapplication.model.qatcheck.punch_point

data class PunchPointUpdate(
    val AssignedTo: String,
    val AssigneeDepartment: String,
    val ClosedBy: String,
    val QAT: String,
    val QATIssueType: String,
    val QATObservation: String,
    val QATPunchPointStatus: String,
    val Recommendation: String,
    val Resolution: String,
    val ResolveDateTime: String,
    val TargetDateTime: String,
    val isActive: Boolean
)