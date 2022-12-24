package com.smarthub.baseapplication.model.siteInfo.opcoInfo

data class OpcoDataItem(
    val Backhaullink: List<Any>,
    val Commercials: List<Any>,
    val Opcoinfo: List<Opcoinfo>,
    val PowerLoad: List<PowerLoadData>,
    val RfAntena: List<RfAnteenaData>,
    val RfEquipment: List<rfEquipmentData>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String,
    val ownername: List<Any>
)