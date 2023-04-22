package com.smarthub.baseapplication.model.qatcheck

data class QATMainLaunchNew(
    val AssignedTo: String,
    val GeoLevel: String,
    val Instruction: String,
    val QATMain: String,
    val QATTemplateMain: List<String>,
    val TargetDateTime: String,
    val isActive: Boolean,
    val AssigneeDepartment: String
)