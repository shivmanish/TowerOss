package com.smarthub.baseapplication.model.siteInfo.qat.qat_main

data class QATMainLaunch(
    val AssignedTo: String,
    val AssignedToUserName: String,
    val AssigneeDepartment: String,
    val AssigneeDepartmentName: String,
    val Category: List<Category>,
    val GeoLevel: String,
    val Instruction: String,
    val QATTemplateMain: List<String>,
    val Siteinfo: String,
    val TargetDateTime: String,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)