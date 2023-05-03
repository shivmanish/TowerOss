package com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra

data class TowerTowerEarthing(
    val ActualCompletionDate: String,
    val StartDAte: String,
    val TAT: Int,
    val VendorEmailId: String,
    val VendorExecutiveName: String,
    val VendorExecutiveNumber: String,
    val attachment: List<Any>,
    val created_at: String,
    val created_by: Int,
    val id: Int,
    val isActive: Boolean,
    val licenseeCompany: Int,
    val modified_at: String,
    val modified_by: Int,
    val remark: String
)