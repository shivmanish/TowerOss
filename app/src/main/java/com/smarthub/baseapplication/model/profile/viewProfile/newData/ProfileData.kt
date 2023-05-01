package com.smarthub.baseapplication.model.profile.viewProfile.newData

import com.smarthub.baseapplication.model.profile.CompanyData

data class ProfileData(
    val active: String,
    val approvedby: String,
    val approveddate: String,
    val blockby: String,
    val blockdate: String,
    val communicationaddress: Communicationaddress,
    val company: String,
    val createddate: String,
    val department: List<String>,
    val email: String,
    val first_name: String,
    val gender: String,
    val id: String,
    val is_active: String,
    val last_name: String,
    val ownercode: String,
    val ownername: String,
    val level: String,
    val maintenancepoint: List<String>,
    val manageremail: String,
    val managername: String,
    val managerphone: String,
    val national: String,
    val officeaddress: Officeaddress,
    val phone: String,
    val priviledgename: List<String>,
    val region: List<String>,
    val requestname: String,
    val roles: List<String>,
    val state: List<String>,
    val title: String,
    val ownernameall: List<CompanyData?>?,
    val username: String
)