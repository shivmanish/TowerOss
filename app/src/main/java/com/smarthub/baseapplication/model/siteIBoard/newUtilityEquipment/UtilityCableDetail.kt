package com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment

data class UtilityCableDetail(
    val CableName: List<Int>,
    val CableType: String,
    val InstallationDate: String,
    val Length: String,
    val LinkId: Any,
    val Remark: String,
    val SerialNumber: String,
    val UsedFor: String,
    val VendorCode: String,
    val VendorCompany: List<Int>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)