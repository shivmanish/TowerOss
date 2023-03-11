package com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency

data class RadioAnteenaDetails(
    val AzimuthOrientation: String,
    val Count: Int,
    val Height: String,
    val InstallationDate: String,
    val Make: String,
    val Model: String,
    val No: Int,
    val OperationStatus: List<Int>,
    val Remark: String,
    val SerialNo: String,
    val Shape: Int,
    val SizeB: String,
    val SizeH: String,
    val SizeL: String,
    val TrxCount: Int,
    val Type: String,
    val Weight: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)