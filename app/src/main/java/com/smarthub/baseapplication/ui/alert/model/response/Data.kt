package com.smarthub.baseapplication.ui.alert.model.response

import com.smarthub.baseapplication.ui.alert.model.chat.ChatModelData

data class Data(
    val Happened: String,
    val Happening: String,
    val HeppenDateTime: String,
    val ReportedBy: String,
    val SACategory: String,
    val SADetails: String,
    val SAIssueType: String,
    val SASeverity: String,
    val SASupportRequired: String,
    val SATroubleMaker: String,
    val Sendalertsupportdata: List<Sendalertsupportdata>,
    val Sendalerttoalldata: List<Sendalerttoalldata>,
    val UserFirstName: String,
    val UserLastName: String,
    val WillHappen: String,
    val created_at: String,
    val emailSend: String,
    val id: String,
    val notiFication: String,
    val sendalerttoall: String,
    val siteid: String,
    val sitename: String,
    val smsSend: String,
    val chats: List<ChatModelData>
)