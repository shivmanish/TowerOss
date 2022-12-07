package com.smarthub.baseapplication.model.register

data class RegisterData(
    var save: String= "NA",
    var ownername: String= "NA",
    var username: String= "NA",
    var last_name: String,
    var phone: String = "NA",
    var email: String = "NA",
    var rolestxt: String = "NA",
    var departmenttxt: String = "M",
    var rolegeographytxt: String= "NA",
    var managername: String = "NA",
    var manageremail: String = "NA",
    var managerphone: String = "NA",
    var password: String = ""
)