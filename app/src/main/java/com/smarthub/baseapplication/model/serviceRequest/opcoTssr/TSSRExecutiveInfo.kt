package com.smarthub.baseapplication.model.serviceRequest.opcoTssr

data class TSSRExecutiveInfo(
    val EmailID: String,
    val ExecutiveName: String,
    val PhoneNumber: String,
    val SurveyDate: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)