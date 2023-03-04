package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SAcqAgreementDetail(
    val Acquisitionarea: String,
    val AgreementEffectiveDate: String,
    val AgreementExpiryDate: String,
    val Costcentre: List<Int>,
    val GroundAcquiredArea: String,
    val GroundUsableArea: String,
    val LastRevisedRentAmount: String,
    val RegistrationDate: String,
    val RegistrationNumber: String,
    val Remark: String,
    val RentStartDate: String,
    val RooftopUsableArea: String,
    val RooftopacquiredArea: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)