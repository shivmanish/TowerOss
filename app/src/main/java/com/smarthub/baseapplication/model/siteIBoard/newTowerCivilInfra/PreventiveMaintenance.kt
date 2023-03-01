package com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra

data class PreventiveMaintenance(
    val NextPMInterval: String,
    val PMDate: String,
    val Remark: String,
    val ServiceType: String,
    val VendorCode: String,
    val VendorCompany: List<Int>,
    val VendorExecutiveName: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)