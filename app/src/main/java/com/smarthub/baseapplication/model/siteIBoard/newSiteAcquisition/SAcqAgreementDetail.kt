package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SAcqAgreementDetail(
    var Acquisitionarea: String,
    var AgreementEffectiveDate: String,
    var AgreementExpiryDate: String,
    var Costcentre: ArrayList<Int>,
    var GroundAcquiredArea: String,
    var GroundUsableArea: String,
    var LastRevisedRentAmount: String,
    var RegistrationDate: String,
    var RegistrationNumber: String,
    var Remark: String,
    var RentStartDate: String,
    var RooftopUsableArea: String,
    var RooftopacquiredArea: String,
    var created_at: String?=null,
    var id: Int,
    var isActive: Boolean?=null,
    var modified_at: String?=null
)