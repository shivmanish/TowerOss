package com.smarthub.baseapplication.model.logs

data class LogsDataList(
    val ChangeLog: List<LogsDataInfo>,
    val id: String,
    val isActive: String
)