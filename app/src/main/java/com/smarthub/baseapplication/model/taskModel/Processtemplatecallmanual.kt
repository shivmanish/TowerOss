package com.smarthub.baseapplication.model.taskModel

data class Processtemplatecallmanual(
    var AssigneeDepartment: String,
    var Automaticescalationofoverdueitems: Boolean,
    var FeatureName: String,
    var NotificationSettingfornewaction: Boolean,
    var PrerequisiteTask: String,
    var Reminderofoutstandingactions: Boolean,
    var SLA: Int,
    var Taskid: Int,
    var Taskinstruction: String,
    var Taskname: String,
    var Weightage: String,
    var Where: String,
    var Workflow: String,
    var actorname: String,
    var documents: Boolean,
    var enddate: String,
    var geolevel: String,
    var pictures: Boolean,
    var priority: String,
    var remark: String,
    var role: String,
    var siteid: String,
    var sitename: String,
    var startdate: String
)