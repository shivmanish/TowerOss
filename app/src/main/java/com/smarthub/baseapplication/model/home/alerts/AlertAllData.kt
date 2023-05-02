package com.smarthub.baseapplication.model.home.alerts

data class AlertAllData(
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
    val Sendalertsupportdata_name: String,
    val Sendalerttoalldata: List<Any>,
    val Sendalerttoalldata_name: String,
    val TentativeDate: String,
    val UserFirstName: String,
    val UserLastName: String,
    val WillHappen: String,
    val alertId: String,
    val attachment: List<Any>,
    val chats: List<Any>,
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