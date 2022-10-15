package com.smarthub.baseapplication.model.register

data class RegisterData(
    var commucationaddess: Commucationaddess = Commucationaddess(),
    var department: String = "NA",
    var email: String = "NA",
    var gender: String = "M",
    var last_name: String = "NA",
    var maintenancepoint: List<String> = ArrayList<String>(),
    var national: String = "NA",
    var officeaddress: Officeaddress = Officeaddress(),
    var ownername: String = "NA",
    var phone: String = "NA",
    var priviledgename: List<String> = ArrayList<String>(),
    var region: List<String> = ArrayList<String>(),
    var requestname: String = "NA",
    var roles: List<String> =ArrayList<String>(),
    var save: String= "NA",
    var state: List<String> = ArrayList<String>(),
    var title: String = "NA",
    var username: String= "NA"
)