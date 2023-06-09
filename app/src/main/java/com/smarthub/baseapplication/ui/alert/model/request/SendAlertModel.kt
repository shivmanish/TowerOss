package com.smarthub.baseapplication.ui.alert.model.request

import com.smarthub.baseapplication.utils.AppController

data class SendAlertModel(
    var Happened: String="True",
    var Happening: String ="True",
    var HeppenDateTime: String="2023-01-03 12:12",
    var SACategory: String="1",
    var SADetails: String="SADetails",
    var SAIssueType: String="1",
    var SASeverity: String="1",
    var SASupportRequired: String="True",
    var SATroubleMaker: String="1",
    var SendAlert: String="",
    var SendAlertUsers: ArrayList<SendAlertUser>? = null,
    var SupportRequiredUsers: ArrayList<SupportRequiredUser>? = null,
    var WillHappen: String="True",
    var emailSend: String="True",
    var notiFication: String="True",
    var sendalerttoall: String="True",
    var siteid: String="448",
    var sitename: String="sitename",
    var ownername: String ?= AppController.getInstance().ownerName,
    var smsSend: String="True"
)