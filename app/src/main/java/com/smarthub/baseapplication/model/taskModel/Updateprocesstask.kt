package com.smarthub.baseapplication.model.taskModel

data class Updateprocesstask(
    val AssigneeDepartment: String,
    val Automaticescalationofoverdueitems: String,
    val FeatureName: String,
    val NotificationSettingfornewaction: String,
    val PrerequisiteTask: String,
    val ReWorkflow: String,
    val Reminderofoutstandingactions: String,
    val SLA: Int,
    val Status: String,
    val Taskid: Int,
    val Taskinstruction: String,
    val Taskname: String,
    val Weightage: String,
    val Where: String,
    val Workflow: String,
    val actorname: String,
    val created_at: String,
    val enddate: String,
    val id: String,
    val stratdate: String,
    val workorderid: String
)