package com.smarthub.baseapplication.model.siteInfo

data class Area(
    val area: String,
    val code: Any,
    val createddate: String,
    val id: Int,
    val modifieddate: String,
    val objectname: List<Objectname>,
    val ownername: List<OwnernameX>
)