package com.smarthub.baseapplication.ui.alert.model.newData

import com.smarthub.baseapplication.ui.alert.model.chat.ChatModelData
import com.smarthub.baseapplication.ui.alert.model.response.Sendalertsupportdata
import com.smarthub.baseapplication.ui.alert.model.response.Sendalerttoalldata

data class NewData(
    val ActionBy: String,
    val ActionFirstName: String,
    val ActionLastName: String,
    val ActionStatus: String,
    val Files: List<Any>,
    val FullDetails: String,
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
    val TentativeDate: String,
    val UserFirstName: String,
    val UserLastName: String,
    val WillHappen: String,
    val chats: List<ChatModelData>?=ArrayList(),
    val created_at: String,
    val emailSend: String,
    val id: String,
    val modified_at: String,
    val notiFication: String,
    val sendalerttoall: String,
    val siteid: String,
    val sitename: String,
    val smsSend: String
)