package com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment

data class UtilityConsumableMaterial(
    val InstallationDate: String,
    val ItemName: String,
    val ItemType: String,
    val Make: String,
    val Model: String,
    val UOM: String,
    val UsedQty: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)