package com.smarthub.baseapplication.ui.alert.model.newData

data class Chat(
    val Files: List<Any>,
    val ReportedBy: String,
    val UserFirstName: String,
    val UserLastName: String,
    val created_at: String,
    val id: String,
    val message: String
)