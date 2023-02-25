package com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency

data class RRUDetails(
    val Count: Int,
    val Frequency: String,
    val Height: String,
    val InstallationDate: String,
    val InstallationLocation: String,
    val Make: String,
    val MaxPowerRating: String,
    val Model: String,
    val No: Int,
    val OperationStatus: List<Int>,
    val Remark: String,
    val Sector: String,
    val SerialNo: String,
    val SizeB: String,
    val SizeH: String,
    val SizeL: String,
    val Type: String,
    val VoltageMax: String,
    val VoltageMin: String,
    val Weight: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)