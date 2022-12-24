package com.smarthub.baseapplication.model.siteInfo.opcoInfo

data class RfAnteenaData(
    val AntenaMake: String,
    val AntenaModel: String,
    val AntenaOrentiation: String,
    val AntenaSerialNumber: String,
    val AntenaSize: String,
    val AntenaTilt: String,
    val AntenaTotalPowerRating: String,
    val AntenaType: String,
    val AntenaWeight: String,
    val CPRICableLength: String,
    val FeederCableLength: String,
    val GPSCableLength: String,
    val InstallationDate: String,
    val InstalledHeight: String,
    val PowerCableLength: String,
    val Remarks: String,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String,
    val ConsumableMaterial: List<Any>

)