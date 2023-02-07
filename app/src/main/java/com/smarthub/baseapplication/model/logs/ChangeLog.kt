package com.smarthub.baseapplication.model.logs

data class ChangeLog(
    val Activity: String,
    val ChangesMade: String,
    val Description: String,
    val IPAddressAngular: String,
    val isActive: Boolean
)