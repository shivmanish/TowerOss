package com.smarthub.baseapplication.model.register

data class RegisterData(
    val commucationaddess: Commucationaddess,
    val department: String,
    val email: String,
    val gender: String,
    val last_name: String,
    val maintenancepoint: List<String>,
    val national: String,
    val officeaddress: Officeaddress,
    val ownername: String,
    val phone: String,
    val priviledgename: List<String>,
    val region: List<String>,
    val requestname: String,
    val roles: List<String>,
    val save: String,
    val state: List<String>,
    val title: String,
    val username: String
)