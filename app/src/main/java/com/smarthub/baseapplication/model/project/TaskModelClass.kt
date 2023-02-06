package com.smarthub.baseapplication.model.project

data class TaskModelClass(
    val AssigneeDepartment: String,
    val Automaticescalationofoverdueitems: Boolean,
    val FeatureName: String,
    val NotificationSettingfornewaction: Boolean,
    val PrerequisiteTask: String,
    val ReWorkflow: String,
    val Reminderofoutstandingactions: Boolean,
    val SLA: String,
    val Status: String,
    val Taskid: String,
    val Taskinstruction: String,
    val Taskname: String,
    val Weightage: String,
    val Where: String,
    val Workflow: String,
    val id: String,
    val Sequenceid:String,
    val geolevel:String,
    val pictures:String,
    val documents:String,
    val isCancel:String,
    val reOpen:String

)