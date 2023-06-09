package com.smarthub.baseapplication.model.project

data class ProjectModelDataItem(
    val Processcolor: String,
    val Processname: String,
    val Remarks: String,
    val Status: String,
    val Total_SLA: Int,
    val Total_Task: Int,
    val created_at: String,
    val created_by: String,
    val id: String,
    val modified_at: String,
    val modified_by: String,
    val siteid:String,
    val sitename:String,
    val Multi:Boolean,
    val Projectname:String,
    val Currentstatus:String,
    val Total_Closed:Int,
    val isCancel:Boolean

)