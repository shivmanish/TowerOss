package com.smarthub.baseapplication.model.siteInfo

data class OpcoContactDetail(
    val OpcoCommercialEmailid: String,
    val OpcoCommercialNumber: String,
    val OpcoCommercialname: String,
    val OpcoSpocEmailid: String,
    val OpcoSpocNumber: String,
    val OpcoSpocname: String,
    val OperatorMaintenanceLocation: String,
    val Remarks: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)