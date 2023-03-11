package com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment

data class UtilityAccessory(
    val InstallationDate: String,
    val ItemName: String,
    val Make: String,
    val Model: String,
    val Quantity: String,
    val Remark: String,
    val SrNumber: Int,
    val Type: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)