package com.smarthub.baseapplication.model.qatcheck.punch_point

data class QatPunchPointModel(
    val PunchPoint: List<PunchPointUpdate>,
    val id: String,
    val ownername: String
)