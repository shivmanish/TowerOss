package com.smarthub.baseapplication.model.home

data class HomeResponse(
    val MyTask: List<MyTeamTask>?,
    val MyTeamTask: List<MyTeamTask>?,
    val Sitestatus: List<Sitestatu>?
)