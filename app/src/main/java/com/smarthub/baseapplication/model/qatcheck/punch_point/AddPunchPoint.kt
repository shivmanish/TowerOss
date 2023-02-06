package com.smarthub.baseapplication.model.qatcheck.punch_point

data class AddPunchPoint(
    val Description: String,
    val QATObservation: String,
    val Remark: String,
    val TargetDateTime: String
)