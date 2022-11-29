package com.smarthub.baseapplication.model.siteInfo

data class Operator(
    val Backhaullink: List<Any>,
    val Commercials: List<Commercial>,
    val Opcoinfo: List<Opcoinfo>,
    val PowerLoad: List<Any>,
    val RfAntena: List<Any>,
    val RfEquipment: List<RfEquipment>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String,
    val ownername: List<Any>
)