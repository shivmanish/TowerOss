package com.smarthub.baseapplication.model.serviceRequest.log

data class ChangeLog(
    val Activity: String,
    val ChangesMade: String,
    val Description: String,
    val IPAddressAngular: String,
    val IPAddressPython: String,
    val Siteinfo: String,
    val User: String,
    val Username: String,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)