package com.smarthub.baseapplication.model.profile

import com.smarthub.baseapplication.model.register.Commucationaddess
import com.smarthub.baseapplication.model.register.Officeaddress

data class UserProfileUpdate(
    var commucationaddess: Commucationaddess = Commucationaddess(),
    var department: String = "NA",
    var email: String = "NA",
    var gender: String = "M",
    var last_name: String = "NA",
    var maintenancepoint: List<String> = ArrayList<String>(),
    var national: String = "NA",
    var officeaddress: Officeaddress = Officeaddress(),
    var ownername: String = "NA",
    var phone: String = "7269024641",
    var priviledgename: List<String> = ArrayList<String>(),
    var region: List<String> = ArrayList<String>(),
    var requestname: String = "NA",
    var roles: List<String> =ArrayList<String>(),
    var update: String= "46",
    var state: List<String> = ArrayList<String>(),
    var title: String = "NA",
    var username: String= "NA"
)