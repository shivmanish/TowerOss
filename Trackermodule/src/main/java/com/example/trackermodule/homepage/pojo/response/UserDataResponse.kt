package com.example.patrollerapp.homepage.pojo.response

data class UserDataResponse(
    val ID: String,
    val Name: String,
    val ccode: String,
    val frtnumber: String,
    val logintime: String,
    val maintancepoint: String,
    val managernumber: String,
    val mandatory: Boolean,
    val status: String,
    val Message: String,
    val validatealertlist: Validatealertlist
)