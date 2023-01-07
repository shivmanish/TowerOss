package com.smarthub.baseapplication.model.siteInfo.utilitiesEquip

data class ConnectedLoad(
    val ActualReading: String,
    val ConnectedEquipment: String,
    val InstallationDate: String,
    val MCBNumber: String,
    val MCBRatingAmp: String,
    val Remarks: String,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)