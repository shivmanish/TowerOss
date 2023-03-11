package com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment

data class UtilityConnectedLoad(
    val ActualReading: String,
    val ConnectedEquipment: String,
    val InstallationDate: String,
    val MCBNumber: Int,
    val RatingAmp: String,
    val Remark: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)