package com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment

data class UtiltyInstallationAcceptence(
    val AcceptanceDate: String,
    val AcceptanceStatus: List<Int>,
    val InputCurrent: String,
    val InputVoltage: String,
    val InstallationDate: String,
    val OutputCurrent: String,
    val OutputVoltage: String,
    val Remark: String,
    val VendorCode: String,
    val VendorCompany: List<Int>,
    val VendorEmailId: String,
    val VendorExecutiveName: String,
    val VendorExecutiveNumber: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)