package com.smarthub.baseapplication.model.siteInfo

data class Objectname(
    val code: Any,
    val createddate: String,
    val id: Int,
    val modifieddate: String,
    val objectname: String,
    val ownername: List<Ownername>,
    val suboperator: List<Any>,
    val suboperator1: List<Any>,
    val suboperator2: List<Any>,
    val suboperator3: List<Any>
)