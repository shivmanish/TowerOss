package com.smarthub.baseapplication.model.siteInfo.qat

data class QatCardItem(
    val Name: String,
    val created_at: String,
    val id: String,
    val isActive: String,
    val Purpose: String,
    val QATTrigger: String,
    val QATStatus: String,
    val QATTemplate: List<QatTemplateModel>,
    val modified_at: String
)