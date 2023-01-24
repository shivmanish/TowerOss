package com.smarthub.baseapplication.ui.alert.model.chat

data class ChatModelData(
    val Files: List<Any>,
    val ReportedBy: String,
    val UserFirstName: String,
    val UserLastName: String,
    val created_at: String,
    val id: String,
    val message: String
)